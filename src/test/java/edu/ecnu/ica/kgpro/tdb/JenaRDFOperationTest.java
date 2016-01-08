package edu.ecnu.ica.kgpro.tdb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.dao.JenaRDFOperation;
import edu.ecnu.ica.kgpro.dao.Operation;
import edu.ecnu.ica.kgpro.dao.BasicOperation;
import edu.ecnu.ica.kgpro.dao.BasicOperationImpl;
import edu.ecnu.ica.kgpro.util.Selector;
import edu.ecnu.ica.kgpro.util.SimpleTripleSelector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public class JenaRDFOperationTest {

	@Test
	public void test() {
		Entity zwq = new Entity("zwq");
		Relation sex = new Relation("sex");
		Triple triple = new Triple(zwq, sex, "男");
		
		BasicOperation dao = new BasicOperationImpl(new JenaRDFOperation());
		
		Predicate<Triple> predicate = (Triple t) -> {
			return (t.getObject() instanceof Integer) && (Integer)t.getObject() > 500
					&& (Integer)t.getObject() < 550;
		};
		
		TripleIterator iterator = dao.query(new SimpleTripleSelector(zwq, null, 500));
		while(iterator.hasNext()) {
			Triple t = iterator.next();
			System.out.println(t);
		}
	}

}
