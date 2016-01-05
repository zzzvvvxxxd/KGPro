package edu.ecnu.ica.kgpro.util;

import java.util.function.Predicate;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;

public interface Selector extends Predicate<Triple>{
	  
	Entity getSubject();
	
	Relation getRelation();
	
	Object getObject();
}
