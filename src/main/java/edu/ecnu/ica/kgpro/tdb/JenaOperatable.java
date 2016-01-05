package edu.ecnu.ica.kgpro.tdb;

import java.util.ArrayList;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;

/**
 * TDB事务原子操作类
 * @author zwq
 *
 */
public class JenaOperatable {
	protected static Dataset db;
	static{
		db = TDBConnection.getDataSet();
	}
	
	protected final void WRITE() {
		db.begin(ReadWrite.WRITE);
	}
	
	protected final void READ() {
		db.begin(ReadWrite.READ);
	}
	
	protected final void END_AND_COMMIT() {
		db.commit();
		db.end();
	}
	
	protected static final void COMMIT() {
		db.commit();
	}
	
	protected static final void END() {
		db.end();
	}
}
