1. 加载JDBC驱动程序： 
	Class.forName("com.mysql.jdbc.Driver") ; 
2. 提供JDBC连接的URL：
	String url="jdbc: mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8;"
3. 创建数据库的连接 
	Connection con=DriverManager.getConnection(url,username,password ) ;
4. 创建一个Statement
   1. 执行静态SQL语句。通常通过Statement实例实现。   
   2. 执行动态SQL语句。通常通过PreparedStatement实例实现。   
   3. 执行数据库存储过程。通常通过CallableStatement实例实现。
   Statement stmt = con.createStatement() ;   
   PreparedStatement pstmt = con.prepareStatement(sql) ;   
   CallableStatement cstmt =con.prepareCall("{CALL demoSp(? , ?)}") ;
5. 执行SQL语句   
	Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate   
   和execute
   1. ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句，返回一个结果集（ResultSet）对象。   
   2. int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等   
   3. execute(sqlString):用于执行返回多个结果集、多个更新计数或二者组合的语句。
    ResultSet rs = stmt.executeQuery("SELECT * FROM ...") ;   
    int rows = stmt.executeUpdate("INSERT INTO ...") ;   
    boolean flag = stmt.execute(String sql) ; 
6. 处理结果 
 	1. 执行更新返回的是本次操作影响到的记录数
 	2. 执行查询返回的结果是一个ResultSet对象。   
    • ResultSet包含符合SQL语句中条件的所有行，并且它通过一套get方法提供了对这些   
      行中数据的访问。   
    • 使用结果集（ResultSet）对象的访问方法获取数据：   
     while(rs.next()){   
         String name = rs.getString("name") ;   
    String pass = rs.getString(1) ; // 此方法比较高效   
     }   
    （列是从左到右编号的，并且从列1开始） 
7. 关闭JDBC对象 
	操作完成以后要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声   
     明顺序相反：   
     1、关闭记录集   
     2、关闭声明   
     3、关闭连接对象
