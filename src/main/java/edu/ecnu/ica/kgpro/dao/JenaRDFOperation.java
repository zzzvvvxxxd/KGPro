package edu.ecnu.ica.kgpro.dao;

import java.util.List;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.ModelCom;
import org.apache.jena.rdf.model.impl.StmtIteratorImpl;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.util.iterator.WrappedIterator;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.tdb.JenaOperatable;
import edu.ecnu.ica.kgpro.tdb.TDBConnection;
import edu.ecnu.ica.kgpro.tdb.TDBUtil;
import edu.ecnu.ica.kgpro.util.Selector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

/**
 * db 目前是单例，需要后续的线程池模式
 * @author zwq
 *
 */
public class JenaRDFOperation extends JenaOperatable implements Operation{
	
	/**
	 * 向Jena数据库插入一条三元组
	 */
	public boolean insert(Triple triple) {
		try {
			WRITE();
			Model model = db.getNamedModel(TDBConnection.MODELURI);
			if(triple.isEntity()) {
				Resource object = model.createResource(
						((Entity)(triple.getObject())).getURI()
						);
				model.createResource(triple.getSubject().getURI())
					 .addProperty(model.createProperty(triple.getRelation().getURI()), object);
			} else if(triple.isString()) {
				model.createResource(triple.getSubject().getURI())
				     .addProperty(model.createProperty(triple.getRelation().getURI()),
				    		                           triple.getObject().toString());
			} else {
				model.createResource(triple.getSubject().getURI())
					 .addLiteral(model.createProperty(triple.getRelation().getURI()),
							                          triple.getObject());
			}
			END_AND_COMMIT();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean insert(List<Triple> list) {
		try {
			WRITE();
			Model model = db.getNamedModel(TDBConnection.MODELURI);
			for(Triple triple : list) {
				if(triple.isEntity()) {
					Resource object = model.createResource(
							((Entity)(triple.getObject())).getURI()
							);
					model.createResource(triple.getSubject().getURI())
						 .addProperty(model.createProperty(triple.getRelation().getURI()), object);
				} else if(triple.isString()) {
					model.createResource(triple.getSubject().getURI())
					     .addProperty(model.createProperty(triple.getRelation().getURI()),
					    		                           triple.getObject().toString());
				} else {
					model.createResource(triple.getSubject().getURI())
						 .addLiteral(model.createProperty(triple.getRelation().getURI()),
								                          triple.getObject());
				}
			}
			END_AND_COMMIT();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 从数据库中删除一条Triple
	 */
	public boolean delete(Triple triple) {
		WRITE();
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		if(triple.isEntity()) {
			// entity
			model.remove(model.getResource(triple.getSubject().getURI()),
					model.getProperty(triple.getRelation().getURI()),
					model.getResource(((Entity)(triple.getObject())).getURI()));
		} else if (triple.isString()) {
			model.remove(model.listStatements(
					model.getResource(triple.getSubject().getURI()),
					model.getProperty(triple.getRelation().getURI()),
				    model.createLiteral(triple.getObject().toString())));
		} else {
			model.remove(model.listStatements(
					model.getResource(triple.getSubject().getURI()),
					model.getProperty(triple.getRelation().getURI()),
					model.createTypedLiteral(triple.getObject())));
		}
		END_AND_COMMIT();
		return false;
	}

	
	/**
	 * 删除一个Entity，以及所有相关的Triple
	 * @param entity 需要删除的实体
	 * @author zwq
	 */
	public boolean delete(Entity entity) {
		WRITE();
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		model.remove(model.getResource(entity.getURI()).listProperties());
		END_AND_COMMIT();
		return false;
	}
	
	
	/**
	 * 删除指定entity的指定relation信息
	 */
	public boolean delete(Entity entity, Relation relation) {
		WRITE();
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		model.remove(model.getResource(entity.getURI())
				.listProperties(model.getProperty(relation.getURI())));
		END_AND_COMMIT();
		return false;
	}

	public boolean update(Triple triple) {
		// 使用insert方法替代
		return insert(triple);
	}

	public TripleIterator query() {
		READ();
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		StmtIterator iterator = model.listStatements();
		END();
		return (new TripleIterator(iterator));
	}
	
	public TripleIterator query(Selector selector) {
		READ();
		Model model = db.getNamedModel(TDBConnection.MODELURI);        // get model
		Graph graph = model.getGraph();                                // get graph of our model
		Object object = selector.getObject();
		StmtIterator i = null;
		ModelCom com = new ModelCom(graph);
		// object 可能为Entity.Class String.Class OtherObject 分别处理
		if(object instanceof Entity) {
			ExtendedIterator<org.apache.jena.graph.Triple> iterator = graph.find(
					asNode(getResource(selector.getSubject(), model)),
					asNode(getProperty(selector.getRelation(), model)),
					asNode(getResource((Entity)(selector.getObject()), model)));   // 查找符合条件的Triple
			i = new StmtIteratorImpl( WrappedIterator.create(iterator).mapWith( t ->  com.asStatement(t)));
		} else if (object instanceof String) {
			ExtendedIterator<org.apache.jena.graph.Triple> iterator = graph.find(
					asNode(getResource(selector.getSubject(), model)),
					asNode(getProperty(selector.getRelation(), model)),
					asNode(getLiteral((String)(selector.getObject()), model)));
			i = new StmtIteratorImpl( WrappedIterator.create(iterator).mapWith( t ->  com.asStatement(t)));
		} else {
			ExtendedIterator<org.apache.jena.graph.Triple> iterator = graph.find(
					asNode(getResource(selector.getSubject(), model)),
					asNode(getProperty(selector.getRelation(), model)),
					asNode(getTypedLiteral(object, model)));
			i = new StmtIteratorImpl( WrappedIterator.create(iterator).mapWith( t ->  com.asStatement(t)));
		}
		// 隐患是，如果object是除了Entity、String和基本数据类型之外的数据类型，则可能出现未知错误
		// 解决：
		//     Typearr = Class{String.Class, Entity.Class, Integer.Class, ...}
		//     if (arr.contains(object.Class)) {
		//         // query thing
		//     }
		//db.close();
		END();
		return new TripleIterator(i);
	}
	
	/**
	 * RDFNode convert to Node
	 * @from ModelCom.Class
	 * @param x
	 * @return
	 */
	private static Node asNode(RDFNode x)
    { return x == null ? Node.ANY : x.asNode(); }
	
	public Resource getResource(Entity entity, Model model) {
		if(entity == null) return null;
		return model.getResource(entity.getURI());
	}
	
	public Property getProperty(Relation relation, Model model) {
		if(relation == null) return null;
		return model.getProperty(relation.getURI());
	}
	
	public Literal getLiteral(String object, Model model) {
		if(object == null) return null;
		return model.createLiteral(object);
	}
	
	public Literal getTypedLiteral(Object object, Model model) {
		if(object == null) return null;
		return model.createTypedLiteral(object);
	}
	
	public static void main(String[] args) {
		Entity entity = new Entity("zwq");
	}
}
