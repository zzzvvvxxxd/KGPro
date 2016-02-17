package edu.ecnu.ica.kgpro.base;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import edu.ecnu.ica.kgpro.tdb.JenaOperatable;

public class Thing{
	static final String DEFAULT_NAMESPACE = "http://www.ica.stc.sh.cn/kg/";
	protected String base = DEFAULT_NAMESPACE;
	protected String name = null;

	public Thing() {
		this.name = "null";
	}
	/**
	 * Constructor
	 * @param n the name of the thing
	 */
	public Thing(String n) {
		this.name = n;
	}
	
	/**
	 * Constructor
	 * @param n the name of the thing
	 * @param b the base(namespace) of URI
	 */
	public Thing(String n, String b) {
		this.name = n;
		this.base = b;
	}
	
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getURI() {
		return this.base + this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Thing) {
			return ((Thing)obj).getName().equals(this.name)
					&& ((Thing)obj).getBase().equals(this.base);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode() + this.base.hashCode();
	}
}
