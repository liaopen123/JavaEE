# JDBC
##概念
java操作数据库，jdbc是Oracle公司制定的一套规范。其作用主要有三点：

 *    链接数据库    
 *    发送sql
 *    处理结果
 
##操作步骤★

1. 先创建好数据库和标
2. 创建一个项目 
3. 导入驱动jar包`mysql-connector-java-5.1.39-bin.jar`
4. 编码


```
    1.  注册驱动
        Class.forName("com.mysql.jdbc.Driver");
    2. 获取链接
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day07","root","123");
    3. 编写sql
    String sql = "select * from category";
    4. 创建预编译的语句执行者
    		PreparedStatement st = conn.prepareStatement(sql);
    5. 设置参数
    6. 执行sql
    ResultSet rs = st.executeQuery();
    7. 处理结果
    while(rs.next()){
				System.out.print(rs.getString("cid")+":::"+rs.getString("cname"));
		}
    8. 释放资源
    rs.close();
	  st.close();
	  conn.close();
```

##对IDE的设置：
1. 在property-workspace 中修改编码为UTF-8;
2. 使用的jdk为自己的jdk，不要使用内置的jdk

##Junit单元测试：
要求：
1.方法应为public void XXX(){};
2.方法上方添加@Test
3.右键-run as -->junit即可运行
##JDBC-API详解：
所有的包都来自于java.sql或者javax.sql，下面介绍常用类和类所对应的方法：
###1.DriverManager
管理一组jdbc的操作
常用方法：

```
1. （了解） 注册驱动 static void registerDriver(Driver driver);
将类加载的到内存中的三种方式：
    1. Class.forName("权限定名");
    2. 类名.class;
    3. 对象.getClass(); 
     
2.（掌握★）获取链接 static void getConnection(String url,String user,String pwd);
其中三个参数：
    url：告诉我们连接什么类型的数据库及连接那个数据库
								协议:数据库类型:子协议 参数
	     例如：mysql:	jdbc:mysql://localhost:3306/数据库名称
						oracle:	jdbc:oracle:thin@localhost:1521@实例
	user：账户名
	pwd:  密码
```	
###2.Connection 
链接  接口
常用方法：

```
  获取语句执行者：
    1. (了解)Statement createStatement() :获取普通的语句执行者  会出现sql注入问题
    2. ★PreparedStatement prepareStatement(String sql) :获取预编译语句执行者
    3. (了解)CallableStatement prepareCall(String sql):获取调用存储过程的语句执行者
  其他方法(了解)：
    setAutoCommit(false) :手动开启事务
	  commit():提交事务
		rollback():事务回滚
```

###3.PreparedStatement:
语句执行者
常用方法：

```
    1. 设置参数
        setXxx(int 第几个问号,Object 实际参数);
					常见的方法:
						 setInt
						 setString
						 setObject
    2. 执行sql
			ResultSet executeQuery() :执行 r 语句 返回值:结果集
			int executeUpdate() :执行cud 语句 返回值:影响的行数
```
###4.ResultSet
结果集  执行查询语句之后返回的结果
常用方法：

    1.boolean next():判断是否有下一条记录,若有返回true且将光标移到下一行,若没有呢则返回false
					光标一开始处于第一条记录的上面
    2.获取具体内容
					getXxx(int|string)
						若参数为int :第几列
						若参数为string:列名(字段名)
					例如:
						获取cname的内容可以通过
							getString(2)
							getString("cname")
	 3.常用方法:
						getInt
						getString 也可以获取int值
						getObject 可以获取任意

 
 
 
##常见的配置文件格式
1. Properties 文件
   里面的内容是key=values格式  放在src目录下  已经都是字符串形式 不需要加引号
   
   **如何读取**：如果配置文件为properties文件，并且放在src目录下，我们可以通过**ResourceBundle**工具快速读取:
   
   ```java
   1. 获取rb对象：
      ResourceBundle.getBundle("文文件名称不带后缀");
   2.通过getString(String key)获取具体的values的值。   
   
##连接池
__需求来源__：在使用JDBC的时候，每操作一次都需要获取连接用完之后再把connection释放掉。因此通过连接池来优化curd操作。

__连接池概述__:C3P0和DBCP;

__作用__:管理数据库的连接，提过啊项目的性能。就是在连接池初始化的时候存入一定数量的连接，用的时候通过方法获取，不用的时候归还连接即可。所有的连接池必须**实现javax.sql.DataSource**接口。

**获取连接池的方法：**

```java
Connection getConnection();
```
归还连接池的方法：

```java
connection.close();
```
###DBCP(理解)
apache出品

使用步骤：
    
    1.导入jar：commons-dbcp-1.4.jar和commons-pool-1.5.6.jar
    2.使用api
     a.硬编码(所有东西写死)
                    //创建连接池
					BasicDataSource ds = new BasicDataSource();
					//配置信息	ds.setDriverClassName("com.mysql.jdbc.Driver");
					ds.setUrl("jdbc:mysql:///day07");
					ds.setUsername("root");
					ds.setPassword("1234");
     b.配置文件类型：
     实现编写一个properties文件
					//存放配置文件
					Properties prop = new Properties();
					prop.load(new FileInputStream("src/dbcp.properties"));
					//设置
					prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
					//创建连接池
					DataSource ds = new BasicDataSourceFactory().createDataSource(prop);
					
					
###C3P0(★掌握)
hibernate和Spring中用到。
有自动回收空闲连接的功能。

使用步骤：
    
    1. 导入jar包：c3p0-0.9.1.2.jar
    2. 使用API
        a.硬编码(不推荐)
        new ComboPoolsDataSource cpds = ne
        b.配置文件
					配置文件的名称:c3p0.properties 或者 c3p0-config.xml
					配置文件的路径:src下
				
					编码只需要一句话
						new ComboPooledDataSource()//使用默认的配置
						new ComboPooledDataSource(String configName)//使用命名的配置 若配置的名字找不到,使用默认的配置
<font color="#ff0000">注意</font>：
    
    1.配置文件配置好后，c3p0自动导入。
    2.若出现：Establishing SSL connection without server's identity verification is not recommended的错误：
    请将url改为：jdbc:mysql://localhost:3306/day07?useUnicode=true&characterEncoding=utf-8&useSSL=false
    其中day07为数据库名

##装饰者模式(静态代理)
用于增强方法
**使用步骤：**
    
    1.装饰者和被装饰者 要实现同一个接口  或者继承同一类
    2.装饰者中要有被装饰着的引用通过构造器传入被装饰的引用。
    3.需要增强的方法进行加强。
    4.对不需要加强的方法调用原来的方法即可。
    [demo可见day07 eclipse项目]
    

##dbUtils
是apache组织的一个工具类，jdbc的框架。更方便我们使用。
使用步骤：

    1. 导入jar包：commons-dbutils-1.4.jar
    2. 使用api：
    创建1个QueryRunner类：
            QueryRunner类的作用：操作sql语句
            构造方法：
                 new QueryRunner(DataSource ds);
    3.编写sql
    4.执行sql：
        query(...):执行R操作
        update(...);执行cud操作    

###核心类和接口
QueryRunner：
    
    作用：操作sql语句
    注意：底层帮我们创建链接，创建语句执行者，释放资源。
    构造器：
        new QuneryRunner(DataSource ds);
    常用方法：
        query();
        update();
        
        
DbUtils:

    作用：施放资源，控制事务。  
    常用方法：
        closeQuietly(Connection conn); 内部处理异常  释放资源
        commitAndClose(Connection conn); 提交事务并且释放链接
              

ResultSetHandler：

    作用：封装结果集。 接口 有9个实现类。    
    ArrayHandler
        将查询结果的第一条记录封装成数组，返回  object[]
    ArrayListHandler
        将查询结果的每一条记录封装成数组，返回 List<Object[]>
    ⭐️⭐️BeanHandler 
        将查询结果的第一条记录封装成指定的bean对象 然后返回
    ⭐️⭐️BeanListHandler
        将查询结果的每一条记录封装成指定的bean对象 然后将bean对象放入list中并返回
    ColumnListHandler
        将查询结果的指定一列放入list中返回
    KeyedHandler 
    MapHandler 
        将查询结果的第一条记录封装成map，字段名作为key  值为value 返回
    ⭐️MapListHandler
        查询结果的第一条记录封装成map，字段名作为key  值为value 将每一个map放入list中 返回
    ⭐️ScalarHandler
        针对于聚合函数返回的  只返回一个值


