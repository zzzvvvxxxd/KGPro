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

为何我们认为我们的操作接口足够强大呢？

```Java
// query(Selector selector);
TripleIterator iterator = dao.query(new SimpleTripleSelector(zwq, null, null));
```
SimpleTripleSelector可以十分灵活地在数据库中过滤Triple
你可以在任意的位置指定或者不指定位置
```Java
new SimpleTripleSelector(zwq, null, null);   // 查找zwq的所有Triple
new SimpleTripleSelector(zwq, sex,  null);   // 查找zwq的sex属性
new SimpleTripleSelector(zwq, name, null);   // 查找所有Relation是name的Triple
```

还不够？
你可以像上面的例子一样为query的返回值添加条件，只要是Predicate<Triple>即可。提供了三种逻辑：
```Java
and(Predicate<Triple>)
or(Predicate<Triple>)
not(Predicate<Triple>)
```

如果我们希望查询Integer属性r1,希望查询出所有r1的值大于100,不小于200的Triple
```

// 编写两条简单的规则
Predicate<Triple t> p1 = (Triple t) -> {
    return (t.getObject() instanseof Integer) && (t.getObject.compareTo(100) > 0);
};
Predicate<Triple t> p2 = (Triple t2) -> {
    return (t.getObject() instanceof String) && (t.getObject.compareTo(200) < 0);
};

//query
TripleIterator iterator = dao.query(new SimpleSelector(null, r1, null)).and(p1).and(p2);
```