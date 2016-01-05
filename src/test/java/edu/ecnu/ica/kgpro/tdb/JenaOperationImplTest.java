package edu.ecnu.ica.kgpro.tdb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.vocabulary.DB;
import org.junit.Test;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.dao.JenaRDFOperation;
import edu.ecnu.ica.kgpro.dao.TripleDAO;
import edu.ecnu.ica.kgpro.dao.TripleDAOImpl;
import edu.ecnu.ica.kgpro.util.SimpleTripleSelector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public class JenaOperationImplTest {

	@Test
	public void testInsert() {
		
		//TDBUtil.clear();
		Entity zwq = new Entity("zwq");
		Relation age = new Relation("age");
		/*
		// Entity
		Entity zwq = new Entity("zwq");
		Entity lyj = new Entity("lyj");
		
		// Relation
		Relation isFriend = new Relation("isFriend");
		Relation age = new Relation("age");
		Relation fullname = new Relation("fullname");
		Relation income = new Relation("income");
	
		// Triple
		Triple t1 = new Triple(zwq, isFriend, lyj);
		Triple t2 = new Triple(zwq, age, 23);
		Triple t3 = new Triple(zwq, fullname, "张巍骞");
		Triple t4 = new Triple(zwq, income, 555.55);
		*/
		
		TripleDAO dao = new TripleDAOImpl(new JenaRDFOperation());
		/*
		dao.insert(t1);
		dao.insert(t2);
		dao.insert(t3);
		dao.insert(t4);
		TDBUtil.print();
		*/
		
		/*
		
		list.add(t1); list.add(t2); list.add(t3); list.add(t4);
		dao.insert(list);
		TDBUtil.print();
		*/
		
		/*
		ArrayList<Triple> list = new ArrayList<>();
		for(int i = 0; i < 1000000; i++) {
			// System.out.println("insert the " + i + "th triple");
			list.add(new Triple(zwq, age, i));
		}
		System.out.println("----");
		dao.insert(list);
		*/
		
		System.out.println();
		
		/* test Triple.toString()
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);
		*/
		
		/* test delete(triple)
		dao.delete(t1);
		TDBUtil.print();
		dao.delete(t2);
		TDBUtil.print();
		dao.delete(t3);
		TDBUtil.print();
		*/
		
		/* test delete(entity, relation)
		dao.delete(zwq, fullname);
		TDBUtil.print();
		*/
		
		
		/* test query()
		TripleIterator iterator = dao.query().and((Triple t) -> {
			if(t.getObject() instanceof Double) {
				return true;
			} else {
				return false;
			}
		});
		*/
		
		
		Predicate<Triple> predicate = (Triple t) -> {
			return (t.getObject() instanceof Integer)
					&& ((Integer)t.getObject() < 550)
					&& ((Integer)t.getObject() > 500);
		};
		
		
		
		
		TripleIterator iterator = dao.query(new SimpleTripleSelector(zwq, age, null)).and(predicate);
		while(iterator.hasNext()) {
			Triple temp = (Triple)iterator.next();
			System.out.println(temp);
		}
		
	
		
		/*
		Iterator iterator = dao.query(
				new SimpleTripleSelector(null, null, 555.55)
				);
		while(iterator.hasNext()) {
			Triple temp = (Triple)iterator.next();
			System.out.println(temp);
		}
		*/

		/* jena object位是否能正确返回类型
		TDBUtil.printObjectType();
		*/
	}
}
