package test.module.jdbc;

import java.io.File;
import java.sql.*;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;


public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        //数据库连接
        Connection connection = null;
        //预编译的Statement，使用预编译的Statement提高数据库性能
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;
        //从配置文件读取数据库配置
        Configurations configs = new Configurations();

        try {
            //读取数据库配置
            PropertiesConfiguration config = configs.properties(new File("src/main/resources/config.properties"));
            String url = config.getString("jdbc.url-1");
            String username = config.getString("jdbc.username-1");
            String password = config.getString("jdbc.password-1");
            // 1. 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newb?useUnicode=true&characterEncoding=utf-8", username, password);
            // 3. 如果要开启事务 需要 设置不自动提交
            connection.setAutoCommit(false);
//            connection =  DriverManager.getConnection(url, username, password);
            // 4. 定义sql语句 ?表示占位符
            String sql = "select * from user where oid = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "3");
            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();

            connection.commit();

            //遍历查询结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("oid") + "  " + resultSet.getString("username"));
            }

            //开启保存点回滚 事务
            // 1. 插入数据
            // 2. 开启 保存点
            Savepoint savepoint1 = connection.setSavepoint("Savepoint1");

            // 3. 插入第二条  失败

            //4. 回滚到保存点
            connection.rollback(savepoint1);

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            // 5 . 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}

