package edu.ecnu.ica.kgpro.util;

import java.util.function.Predicate;

import org.apache.jena.rdf.model.Statement;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;

public class SimpleTripleSelector implements Selector{
	protected Entity subject;
	protected Relation relation;
	protected Object object;

	public SimpleTripleSelector(Entity sub, Relation rel, Object obj) {
		this.subject = sub;
		this.relation = rel;
		this.object = obj;
	}
	
	public boolean test(Triple triple) {
		return (subject == null || subject.equals(triple.getSubject()))
	            && (relation == null || relation.equals(triple.getRelation()))
	            && (object == null || object.equals(triple.getObject()))
	            && selects(triple);
	}
	
	/**
	 * 高级条件
	 * @param s
	 * @return
	 */
	public boolean selects(Triple s) {
        return true;
    }

	public Entity getSubject() {
		return subject;
	}

	public Object getObject() {
		return object;
	}

	public Relation getRelation() {
		return relation;
	}
}
