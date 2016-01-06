package edu.ecnu.ica.kgpro.dao;

import java.util.Iterator;
import java.util.List;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.util.Selector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public class BasicOperationImpl implements BasicOperation{

	//DAO 操作实现类
	private Operation operation;
	
	/**
	 * Constructor
	 * @param operation
	 */
	public BasicOperationImpl(Operation operation) {
		this.operation = operation;
	}

	public boolean insert(Triple triple) {
		return operation.insert(triple);
	}

	public boolean delete(Triple triple) {
		return operation.delete(triple);
	}

	public boolean delete(Entity entity) {
		return operation.delete(entity);
	}

	public boolean delete(Entity entity, Relation relation) {
		return operation.delete(entity, relation);
	}
	
	public boolean update(Triple triple) {
		return operation.update(triple);
	}

	public TripleIterator query() {
		return operation.query();
	}

	public TripleIterator query(Selector selector) {
		return operation.query(selector);
	}

	@Override
	public boolean insert(List<Triple> list) {
		return operation.insert(list);
	}
}
