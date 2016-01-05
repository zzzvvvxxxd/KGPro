package edu.ecnu.ica.kgpro.util;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;

public class Common {
	static public Triple toTriple(Statement statement) {
		Entity subject = new Entity(statement.getSubject().getLocalName(), statement.getSubject().getNameSpace());
		Relation relation = new Relation(statement.getPredicate().getLocalName(), statement.getPredicate().getNameSpace());
		Object tail;
		RDFNode object = statement.getObject();
		if(object instanceof Resource) {
			tail = new Entity(((Resource)object).getLocalName(), ((Resource)object).getNameSpace());
		} else {
			tail = object.asLiteral().getValue();
		}
		return new Triple(subject, relation, tail);
	}
}
