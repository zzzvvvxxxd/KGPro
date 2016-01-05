package edu.ecnu.ica.kgpro.tdb;

import javax.print.DocFlavor.READER;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class TDBUtil {
	private static Dataset db = TDBConnection.getDataSet();
	/**
	 * 判断数据库是否为空
	 * @return
	 */
	static public boolean isEmpty() {
		db.begin(ReadWrite.READ);
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		boolean b = model.isEmpty();
		db.end();
		return b;
	}
	
	/**
	 * 显示数据库中所有的三元组
	 */
	static public void print() {
		db.begin(ReadWrite.READ);
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		StmtIterator iterator = model.listStatements();
		while(iterator.hasNext()) {
			Statement statement = iterator.nextStatement();
			System.out.println(statement.getSubject().toString() + "\t" + 
							   statement.getPredicate().getLocalName().toString() + "\t" +
					           statement.getObject().toString());
		}
		db.end();
	}
	
	/**
	 * 清空数据库
	 */
	static public void clear() {
		db.begin(ReadWrite.WRITE);
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		model.removeAll();
		db.commit();
		db.end();
	}
	
	static public void printObjectType() {
		db.begin(ReadWrite.READ);
		Model model = db.getNamedModel(TDBConnection.MODELURI);
		StmtIterator iterator = model.listStatements();
		while(iterator.hasNext()) {
			Statement statement = iterator.next();
			RDFNode object = statement.getObject();
			System.out.println(object.getClass().getName());
		}
		db.end();
	}
}
