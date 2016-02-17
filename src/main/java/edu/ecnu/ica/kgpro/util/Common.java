package edu.ecnu.ica.kgpro.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;

public class Common {
	public final static String BASE = "http://ica.stc.sh.cn/kg/";
	static public Triple toTriple(Object statement) {
		//处理Statement
		if(statement instanceof Statement) {
			//subject
			String subURI = ((Statement) statement).getSubject().getURI();
			Entity subject = new Entity(subURI.substring(subURI.lastIndexOf("/") + 1, subURI.length()), 
					subURI.substring(0, subURI.lastIndexOf("/") + 1));
			//relation
			String relURI = ((Statement) statement).getPredicate().getURI();
			Relation relation = new Relation(relURI.substring(relURI.lastIndexOf("/") + 1, relURI.length()),
					relURI.substring(0, relURI.lastIndexOf("/") + 1));
			//object
			Object tail;
			RDFNode object = ((Statement) statement).getObject();
			if(object instanceof Resource) {
				String objURI = ((Resource)object).getURI();
				tail = new Entity(objURI.substring(objURI.lastIndexOf("/") + 1, objURI.length()),
						objURI.substring(0, objURI.lastIndexOf("/") + 1));
			} else {
				tail = object.asLiteral().getValue();
			}
			return new Triple(subject, relation, tail);
		}
		
		//处理Excel中的Row
		if(statement instanceof Row) {
			Iterator<Cell> cIterator = ((Row)statement).cellIterator();
			Cell entityCell = cIterator.next();
			Cell relationCell = cIterator.next();
			Cell objectCell = cIterator.next();
			ArrayList<String> a1 = parseShrap(entityCell.toString());
			ArrayList<String> a2 = parseShrap(relationCell.toString());
			ArrayList<String> a3 = parseShrap(objectCell.toString());
			Entity subject = new Entity(a1.get(1), BASE + a1.get(0) + "/");
			Relation relation = new Relation(a2.get(1), BASE + a2.get(0) + "/");
			Entity object = new Entity(a3.get(1), BASE + a3.get(0) + "/");
			return new Triple(subject, relation, object);
		}
		return null;
	}
	
	public static ArrayList<String> parseShrap(String token) {
		String s1 = token.substring(0, token.indexOf('#'));
		ArrayList<String> array = new ArrayList<String>(2);
		array.add(token.substring(0, token.indexOf('#')));
		array.add(token.substring(token.indexOf('#') + 1, token.length()));
		return array;
	}
}
