package edu.ecnu.ica.kgpro.base;


public class Triple {
	protected Entity   subject;
	protected Relation relation;
	protected Object   object;
	
	/**
	 * 构造函数 Constructor
	 * @param sub
	 * @param rel
	 * @param obj
	 */
	public Triple(Entity sub, Relation rel, Object obj) {
		this.subject = sub;
		this.relation = rel;
		this.object = obj;
	}
	
	/**
	 * obj是否为基本数据类型
	 * @return
	 */
	public boolean isLiteral() {
		if((object instanceof Entity) || (object instanceof String))
			return false;
		return true;
	}
	
	public boolean isString() {
		if(object instanceof String)
			return true;
		return false;
	}
	
	/**
	 * obj是否为实体
	 * @return
	 */
	public boolean isEntity() {
		if(object instanceof Entity)
			return true;
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Triple) {
			Triple triple = (Triple)obj;
			System.out.println(subject.equals(triple.getSubject()));
			return subject.equals(triple.getSubject())
					&& relation.equals(triple.getRelation())
					&& object.equals(triple.getObject());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.subject.hashCode() + this.relation.hashCode() + this.object.hashCode();
	}
	
	public Entity getSubject() {
		return subject;
	}

	public void setSubject(Entity subject) {
		this.subject = subject;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append(subject.toString() + "    ");
		sb.append(relation.toString() + "    ");
		sb.append(object.toString() + "(" + object.getClass().getSimpleName() + ") }" );
		return sb.toString();
	}
}
