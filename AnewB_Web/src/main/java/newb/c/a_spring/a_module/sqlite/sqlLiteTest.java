package newb.c.a_spring.a_module.sqlite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther:woshizbh
 * @Date: 2018/11/19
 * @Deseription
 */
public class sqlLiteTest {
    private Connection c ;
    private Statement stmt;

    @Before
    public void conn() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:file:/qhCode/AnewB/test.db");
        System.out.println("1--连接数据库成功");
        stmt = c.createStatement();
    }

    @After
    public void closeConn() throws SQLException {
        if (stmt!=null && !stmt.isClosed())
            stmt.close();

        if(c!=null && !c.isClosed())
            c.close();

    }

    @Test
    public void createTable(){
        try {
            String sql = "CREATE TABLE company " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("-- finish!!!");
    }

    @Test
    public void insert(){
        try {
            c.setAutoCommit(false);

            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("-- finish!!!");
    }


    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:file:/qhCode/AnewB/test.db");
            System.out.println("取到sqlite数据库连接成功");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "DROP TABLE IF EXISTS `user`;\n" +
                    "   CREATE TABLE `user` (\n" +
                    "  `oid` int(11) NOT NULL DEFAULT '0',\n" +
                    "  `username` varchar(255) DEFAULT NULL,\n" +
                    "  `password` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`oid`)\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO `user` VALUES ('1', '1111', '1111');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
    }
}
