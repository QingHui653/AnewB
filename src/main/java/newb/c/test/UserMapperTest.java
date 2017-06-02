package newb.c.test;

import newb.c.backend.dao.UserMapper;
import newb.c.backend.model.basemodel.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by woshizbh on 2017/1/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:main/resources/mybatis/spring-mybatis.xml"})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectByKeyTest(){
        User user = userMapper.testSelectByKey(5);
        System.out.println(user.toString());
    }
}
