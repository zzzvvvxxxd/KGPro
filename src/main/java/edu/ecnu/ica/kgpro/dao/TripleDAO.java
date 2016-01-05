package edu.ecnu.ica.kgpro.dao;

import java.util.Iterator;
import java.util.List;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.util.Selector;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public interface TripleDAO {
	/**
	 * 插入Triple
	 * @param triple
	 * @return
	 */
	public boolean insert(Triple triple);
	
	public boolean insert(List<Triple> triple);
	
	/**
	 * 删除指定的Triple
	 * @param triple
	 * @return
	 */
	public boolean delete(Triple triple);
	
	/**
	 * 删除指定的Entity的所有信息
	 * @param entity
	 * @return
	 */
	public boolean delete(Entity entity);
	
	/**
	 * 删除指定Entity的指定Relation的所有Triple
	 * @param entity
	 * @param relation
	 * @return
	 */
	public boolean delete(Entity entity, Relation relation);

	/**
	 * 更新Entity subject的property为object 
	 * @param subject 主语
	 * @param property 属性
	 * @param object 宾语
	 * @return
	 */
	public boolean update(Triple triple);
	
	/**
	 * 查询全部Triple
	 * @return
	 */
	public TripleIterator query();
	
	/**
	 * 利用选择器查询
	 * @param selector
	 * @return
	 */
	public TripleIterator query(Selector selector);
}
