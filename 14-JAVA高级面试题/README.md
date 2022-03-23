1、Mybatis 分页查询？
Mybatis本身有分页查询，但是并不是正真的分页查询，它是把数据查出来放在内存里面，你想要什么就给你什么。 我们使用Mybatis实现分页查询的时候，是要实现真分页查询，就是要用sql语句来实现分页查询。MySQL和Oracle两种数据库的实现方法是不一样的。 MySQL：select * from table limit N , M; 其中：N表示从第几页开始，M表示每页显示的条数。比如：数据库中有30条数据，要求每页显示10条，显示第2页的所有数据。 SQL语句就可以写成：Limit 10 , 20; Oracle实现分页查询：采用伪列ROWNUM

2、Mybatis能执行一对多，一对一的联系查询吗，有哪些实现方法
能，不止可以一对多，一对一还可以多对多，一对多

实现方式：

1、 单独发送一个SQL去查询关联对象，赋给主对象，然后返回主对象

2、 使用嵌套查询，似JOIN查询，一部分是A对象的属性值，另一部分是关联对 象 B的属性值，好处是只要发送一个属性值，就可以把主对象和关联对象查出来

3、 子查询

3、使用 MyBatis 的 mapper 接口调用时有哪些要求？
1、 Mapper 接口方法名和 mapper.xml 中定义的每个 sql 的 id 相同

2、 Mapper 接口方法的输入参数类型和 mapper.xml 中定义的每个 sql 的 parameterType 的类型相同

3、 Mapper 接口方法的输出参数类型和 mapper.xml 中定义的每个 sql 的 resultType 的类型相同

4、 Mapper.xml 文件中的 namespace 即是 mapper 接口的类路径。

4、MyBatis是什么？
Mybatis 是一个半 ORM（对象关系映射）框架，它内部封装了 JDBC，开发时只需要关注 SQL 语句本身，不需要花费精力去处理加载驱动、创建连接、创建statement 等繁杂的过程。程序员直接编写原生态 sql，可以严格控制 sql 执行性能，灵活度高。

MyBatis 可以使用 XML 或注解来配置和映射原生信息，将 POJO 映射成数据库中的记录，避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。

5、#{}和${}的区别是什么？
Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用Prepared Statement的set方法来赋值；Mybatis在处理
时，就是把
{}替换成变量的值；使用#{}可以有效的防止SQL注入，提高系统安全性。

6、为什么说 Mybatis 是半自动 ORM 映射工具？它与全自动的区别在哪里？
Hibernate 属于全自动 ORM 映射工具，使用 Hibernate 查询关联对象或者关联集合对象

时，可以根据对象关系模型直接获取，所以它是全自动的。而 Mybatis 在查询关联对象或

关联集合对象时，需要手动编写 sql 来完成，所以，称之为半自动 ORM 映射工具。

7、为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？
Hibernate属于全自动ORM映射工具，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。

而Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以，称之为半自动ORM映射工具。

8、什么情况下用注解绑定,什么情况下用 xml 绑定？
当 Sql 语句比较简单时候,用注解绑定；当 SQL 语句比较复杂时候,用 xml 绑定,一般用

xml 绑定的比较多

9、Mybais 常用注解 ？
@Insert ： 插入sql , 和xml insert sql语法完全一样

@Select ： 查询sql, 和xml select sql语法完全一样

@Update ： 更新sql, 和xml update sql语法完全一样

@Delete ： 删除sql, 和xml delete sql语法完全一样

@Param ： 入参

@Results ：结果集合

@Result ： 结果

10、MyBatis的框架架构设计是怎么样的


这张图从上往下看。MyBatis的初始化，会从mybatis-config.xml配置文件，解析构造成Configuration这个类，就是图中的红框。

1、 加载配置：配置来源于两个地方，一处是配置文件，一处是Java代码的注解，将SQL的配置信息加载成为一个个MappedStatement对象（包括了传入参数映射配置、执行的SQL语句、结果映射配置），存储在内存中。

2、 SQL解析：当API接口层接收到调用请求时，会接收到传入SQL的ID和传入对象（可以是Map、JavaBean或者基本数据类型），Mybatis会根据SQL的ID找到对应的MappedStatement，然后根据传入参数对象对MappedStatement进行解析，解析后可以得到最终要执行的SQL语句和参数。

3、 SQL执行：将最终得到的SQL和参数拿到数据库进行执行，得到操作数据库的结果。

4、 结果映射：将操作数据库的结果按照映射的配置进行转换，可以转换成HashMap、JavaBean或者基本数据类型，并将最终结果返回。

11、Mybaits的优点有什么？
12、Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？
13、接口绑定有几种实现方式,分别是怎么实现的?
14、Mybaits的优点：
15、在mapper中如何传递多个参数
16、Mybatis是否可以映射Enum枚举类？
17、Mybatis的一级缓存和二级缓存？
18、Mybatis的映射文件 ？
19、Mybatis 比 IBatis 比较大的几个改进是什么？
20、什么是DBMS
21、什么是MyBatis的接口绑定？有哪些实现方式？
22、Mybatis的Xml映射文件中，不同的Xml映射文件，id是否可以重复？
23、#{}和${}的区别是什么？
24、MyBatis编程步骤是什么样的？
25、如何获取生成的主键