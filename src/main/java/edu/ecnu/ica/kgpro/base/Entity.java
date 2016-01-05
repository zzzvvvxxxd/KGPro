package edu.ecnu.ica.kgpro.base;

import java.util.ArrayList;

public class Entity extends Thing{
	
	String description = null;  // 描述
	
	/**
	 * Constructor
	 * @param n name of entity
	 * @param b base of namespace
	 */
	public Entity(String n, String b) {
		super(n, b);
	}
	
	public Entity(String n) {
		super(n);
	}

	public Entity(Entity other) {
		super(other.name, other.base);
		this.description = other.description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Entity) {
			return ((Entity)obj).getName().equals(this.name)
					&& ((Entity)obj).getBase().equals(this.base);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
