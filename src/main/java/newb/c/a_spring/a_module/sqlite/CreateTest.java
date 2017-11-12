package newb.c.a_spring.a_module.sqlite;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTest {
    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:file:/qhCode/AnewB/test.db");
            System.out.println("取到sqlite数据库连接成功");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            File file = new File("user.sql");
            FileInputStream fio =new FileInputStream(file);
            String sql = IOUtils.toString(fio,"UTF-8");
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
