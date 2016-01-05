package edu.ecnu.ica.kgpro.base;

import static org.junit.Assert.*;

import org.junit.Test;

public class TripleTest {

	@Test
	public void testTriple() {
		Entity zwq = new Entity("zwq");
		Relation relation = new Relation("isFriend");
		Entity lyj = new Entity("lyj");
		Triple triple = new Triple(zwq, relation, lyj);
		
		Entity zwq2 = new Entity("zwq");
		Relation relation2 = new Relation("isFriend");
		Entity lyj2 = new Entity("lyj");
		Triple triple2 = new Triple(zwq2, relation2, lyj2);
		
		System.out.println(triple.equals(triple2));
	}

}
