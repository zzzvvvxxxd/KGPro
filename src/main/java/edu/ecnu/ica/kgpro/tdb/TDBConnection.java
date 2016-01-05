package edu.ecnu.ica.kgpro.tdb;

import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.Configuration;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.vocabulary.DB;

public class TDBConnection {
	private static final String directory = "/home/zwq/Workspace/Java/KGPro/DB/";
	private static Model model = null;
	
	public static final String MODELURI = "http://www.ica.stc.sh.cn/kg/model";
	
	/**
	 * 获取TDB实例
	 * @future 线程池模式
	 * @return 数据库实例
	 */
	public static Dataset getDataSet() {
		return SingletonFactory.instance; 
	}
	
	/**
	 * 内部类来维护TDB实例单例
	 * @author zwq
	 */
	private static class SingletonFactory {
		private static Dataset instance = TDBFactory.createDataset(directory);
	}
}
