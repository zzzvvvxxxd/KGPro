package edu.ecnu.ica.kgpro.tdb;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.dao.JenaRDFOperation;
import edu.ecnu.ica.kgpro.dao.Operation;
import edu.ecnu.ica.kgpro.dao.TripleDAO;
import edu.ecnu.ica.kgpro.dao.TripleDAOImpl;
import edu.ecnu.ica.kgpro.util.Selector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public class JenaRDFOperationTest {

	@Test
	public void test() {
		Entity zwq = new Entity("张巍骞");
		Relation sex = new Relation("sex");
		Triple triple = new Triple(zwq, sex, "男");
		
		TripleDAO dao = new TripleDAOImpl(new JenaRDFOperation());
	}

}
