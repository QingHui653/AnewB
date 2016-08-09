package test;


import java.util.List;

import newb.c.model.User;
import newb.c.service.UserService;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

/**
 * 创建时间：2015-1-27 下午10:45:38
 * 
 * @author andy
 * @version 2.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mybatis.xml" })
public class test {

	private static final Logger LOGGER = Logger.getLogger(test.class);

	@Autowired
	public UserService userService;

	
	@Test
	public void testQueryById1() {
		User userInfo = userService.getUserById(1);
		LOGGER.info(JSON.toJSON(userInfo));
	}


	@Test
	public void testInsert() {
		User userInfo = new User();
		userInfo.setUsername("xiaoz");
		userInfo.setPassword("5");
		int result = userService.insert(userInfo);
		System.out.println(result);
	}
}
