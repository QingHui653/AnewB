package test.module.jdbc;

import newb.c.backend.sql.model.UserTrin;
import newb.c.backend.sql.model.basemodel.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisFirst {

    //根据id查询用户信息，得到一条记录结果
    public void findUserByIdTest() throws IOException{
        // mybatis配置文件
        String resource = "main/resources/mybatis/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过SqlSession操作数据库
        // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
        // selectOne查询出一条记录
        User user = sqlSession.selectOne("UserMapper.findUserById", 1);
        
//        User user2 = sqlSession.selectOne("UserMapper.findUserBySql", "select * from user where oid=2");
        
        System.out.println(user);
//        System.out.println(user2);

        // 释放资源
        sqlSession.close();

    }
    public void findUserByNameTest() throws IOException {
        // mybatis配置文件
    	String resource = "main/resources/mybatis/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // list中的user和映射文件中resultType所指定的类型一致
        List<User> list = sqlSession.selectList("UserMapper.findUserList");
        System.out.println(list.size());
        sqlSession.close();

    }
    
    
    @SuppressWarnings("unused")
    public void business() throws IOException{
        String resource = "main/resources/mybatis/SqlMapConfig.xml";
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//此session为自动提交
        SqlSession sqlSession1 = sqlSessionFactory.openSession(false);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(false);

        // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
        // selectOne查询出一条记录
        //发生脏读 ：脏读就是指当一个事务正在访问数据，并且对数据进行了修改，而这种修改还没有提交到数据库中，这时，另外一个事务也访问 这个数据，然后使用了这个数据。
        //懵逼，发生了脏读 ，没找到原因，查询还是查询以前的数据，但已经关闭缓存
        User user11 = sqlSession1.selectOne("UserMapper.findUserById", 2);
        int isSucc= sqlSession1.delete("UserMapper.delUserById", 2);
        User user1 = sqlSession1.selectOne("UserMapper.findUserById", 2);
        User user2 = sqlSession2.selectOne("UserMapper.findUserById", 2);
        System.out.println("sqlSession1 查询 "+user11);
        System.out.println("sqlSession1 删除 "+isSucc);
        System.out.println("sqlSession1 查询 "+user1);
        System.out.println("sqlSession2 查询 "+user2);//发生脏读
        sqlSession1.commit();
        User user22 = sqlSession2.selectOne("UserMapper.findUserById", 2);
        int isSucc22= sqlSession2.delete("UserMapper.delUserById", 2);
        User user23 = sqlSession2.selectOne("UserMapper.findUserById", 2);
        int isUpdate= sqlSession2.delete("UserMapper.updateUserById", 2);
        System.out.println("提交后sqlSession2 查询 "+user22);
        System.out.println("sqlSession2 删除 "+isSucc22);
        System.out.println("提交后sqlSession2 查询 "+user23);
        System.out.println("sqlSession2 更新 "+isUpdate);
        System.out.println("sqlSession2 查询  发生了脏读"+user23);
        sqlSession2.commit();
        
        // 释放资源
        sqlSession1.close();
        sqlSession2.close();
        sqlSession.close();

    }
    
    @Test
    public void findUserByParam() throws IOException {
        // mybatis配置文件
    	String resource = "main/resources/mybatis/SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // list中的user和映射文件中resultType所指定的类型一致
        UserTrin userTrin =new UserTrin();
        User user=new User();
//        user.setOid(5);
        user.setUsername("5");
//        user.setPassword("5");
        userTrin.setUser(user);
        
        List<User> list = sqlSession.selectList("UserMapper.findUserByParam",userTrin);
        System.out.println(list.size());
        sqlSession.close();

    }
    
}
