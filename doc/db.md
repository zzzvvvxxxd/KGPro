# 知识图谱操作

> 虽然，KGPro希望几乎更多的操作围绕Node展看，但是我们依然提供了足够强大的通用操作接口

# 接口
目前项目采用的是代理模式：
```Java
// 代理类
BasicOperationImpl.Class

// 通用接口
BasicOperation

// 业务类
JenaRDFOperation.Class implements BasicOperation
...

// 抽象通用接口
Operation extends BasicOperation
```

接口内容：
```Java
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
```

##基本示例：
```Java
// 创建Operation
// 选择使用Jena RDF API
BasicOperation dao = new BasicOperationImpl(new JenaRDFOperation());

// insert
dao.insert(triple);

// insert
dao.insert(List<Triple> list);    // 百万级的数据维持在秒级

// delete
dao.delete(Triple);  // 指定Triple
dao.delete(Entity);  // 指定Entity

// query
Predicate<Triple> predicate = (Triple t) -> {
    return (t.getObject() instanceof String) && t.getObject().equals("男");
};
		
TripleIterator iterator = dao.query().and(predicate);

while(iterator.hasNext()) {
    Triple t = iterator.next();
    System.out.println(t);
}
```