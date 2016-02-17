package edu.ecnu.ica.kgpro.tdb;


import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.tdb.TDBFactory;

/**
 * TDB事务原子操作类
 * @author zwq
 *
 */
public class JenaOperatable {
	protected static Dataset db = null;
	protected static String path;
	
	public JenaOperatable(JenaParams params) {
		path = params.get("dbpath"); 
		if(db == null) {
			db = TDBFactory.createDataset(path);
		}
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
	
	protected static final void CLOSE() {
		db.close();
	}
}
