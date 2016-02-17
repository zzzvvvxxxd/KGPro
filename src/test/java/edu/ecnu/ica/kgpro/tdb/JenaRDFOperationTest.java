package edu.ecnu.ica.kgpro.tdb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.dao.BasicOperation;
import edu.ecnu.ica.kgpro.dao.BasicOperationImpl;
import edu.ecnu.ica.kgpro.dao.JenaRDFOperation;
import edu.ecnu.ica.kgpro.ioutil.ExcelUtil;
import edu.ecnu.ica.kgpro.util.Common;
import edu.ecnu.ica.kgpro.util.SimpleTripleSelector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public class JenaRDFOperationTest {

	@Test
	public void test() {
		BasicOperation dao = new BasicOperationImpl(new JenaRDFOperation(
				new JenaParams() {
					@Override
					public String get(String name) {
						switch (name) {
						case "dbpath":
							return "./DB/";
						default:
							return null;
						}
					}
				}
				));
		
		Entity person = new Entity("20", Common.BASE + "baseinfo/");
		TripleIterator result = dao.query(new SimpleTripleSelector(person, null, null));
		while(result.hasNext()) {
			Triple t = result.next();
			System.out.println(t);
		}
	}
}



