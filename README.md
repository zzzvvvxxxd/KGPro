#KGPro

VERSION: 0.0.1

Builde your own Knowledge Graph (not an Ontology).

## 基本概念
|Concept   |      描述             |      备注                |
|:--------:|:--------------------:|:------------------------|
|Thing     |Everything is a Thing |                         |
|Entity    |实体                   |                         |
|Relation  |关系                   |                         |
|Triple    |三元组                 |                         |

> 关于属性和关系
确实，在自然世界中，predicate可以表示两个事物之间的关系，也可以用来描述一个事务的属性（例如：张三的身高是173cm），但是KGPro将这样的区别弱化，并且全部抽象成Relation，即张三的朋友是李四，张三的身高是173cm这样的描述（知识）都抽象成关系。
###Thing
基类  
类似与Java中的Object，Entity和Relation都继承Thing类  

### Entity
创建Entity实例：

```Java
// 1. 提供name
Entity zwq = new Entity("zwq");
// 2. 提供URI(name, base)
Entity zwq = new Entity("zwq", "http://ica.ecnu.edu");
```

###Relation
创建对象的构造方法和Entity类似
Relation isFriend = new Relation("isFriend", "http://ica.ecnu.edu");

###Triple
三元组，内部包含三个原子数据：
```Java
protected Entity   subject;
protected Relation relation;
protected Object   object;
```
在KGPro中，Object试图去表达包含了Entity、String、基本数据类型的所有实例的可能，如下的Triple都是合法的。并且看起来，是可以很好地区分和涵盖自然世界中绝大多数的情况。
```Java
// Triple
Triple t1 = new Triple(zwq, isFriend, lyj);
Triple t2 = new Triple(zwq, age, 23);
Triple t3 = new Triple(zwq, fullname, "张巍骞");
Triple t4 = new Triple(zwq, income, 555.55);
```