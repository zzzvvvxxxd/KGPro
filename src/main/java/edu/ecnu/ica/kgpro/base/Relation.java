package edu.ecnu.ica.kgpro.base;

public class Relation extends Thing{
	
	// 关系描述
	String description = null;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Constructor
	 * @param n
	 * @param b
	 */
	public Relation(String n, String b) {
		super(n, b);
	}
	
	public Relation(String n){
		super(n);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Relation) {
			return ((Relation)obj).getName().equals(this.name)
					&& ((Relation)obj).getBase().equals(this.base);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
