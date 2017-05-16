package newb.c.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import newb.c.backend.model.UserTrin;
import newb.c.backend.model.basemodel.User;
import newb.c.backend.model.basemodel.UserCache;
import newb.c.backend.service.TestCacheService;
import newb.c.backend.service.UserService;

@Controller
@RequestMapping("db")
public class DbController {

	@Autowired
	private UserService userService;
	@Autowired
	private TestCacheService userCacheService;

	public volatile Integer threadCount = 0;

	@RequestMapping(value = "/commonSel", method = RequestMethod.POST)
	@ApiOperation("测试通用mapper 自定义的查询方法")
	@ResponseBody
	public Object commonSel(ModelMap modelMap) {
		List<User> userList = userService.CommonSelMapper("select * from user");
		return userList;
	}

	/**
	 * 测试不同mapXML是否可行
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/selectQW/{userId}", method = RequestMethod.GET)
	@ApiOperation("测试不同的mapxml是否可用")
	@ResponseBody
	public String selectQW(ModelMap modelMap, @PathVariable int userId) {
		String password = userService.selectPW(userId);
		return password;
	}

	/**
	 * 测试dao关联查询 关联查询暂时没找到方法使用注解的方式
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/selectUserDao/{userId}", method = RequestMethod.GET)
	@ApiOperation("测试关联查询，直接使用@select")
	@ResponseBody
	public Object selectUserCacheByDao(ModelMap modelMap, @PathVariable int userId) {
		UserTrin userTrin = userService.selectUserCacheByDao(userId);
		return userTrin;
	}

	/**
	 *
	 * 测试XML关联查询是否可行
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/selectUserCache/{userId}", method = RequestMethod.GET)
	@ApiOperation("测试关联查询，使用xml")
	@ResponseBody
	public Object selectUserCacheByUser(ModelMap modelMap, @PathVariable int userId) {
		UserTrin userTrin = userService.selectUserCacheByUser(userId);
		return userTrin;
	}

	/**
	 * save 测试批量插入
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/save", method = RequestMethod.GET)
	@ApiOperation("测试批量插入方法")
	@ResponseBody
	public String insertUser(ModelMap modelMap) {
		/**
		 * 测试插入10w数据，用时，在服务器开启后第一次用时10s，后面用时2-4s
		 */
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < 10000; i++) {
			User user = new User();
			user.setOid(i);
			user.setUsername(i + "");
			user.setPassword(i + "");
			userList.add(user);
		}
		userService.insertAll(userList);
		/**
		 * 测试查询10w条数据，user在oid加入索引 类型Unique 方法：BTREE
		 */
		/*
		 * Example e= new Example(User.class);
		 * e.createCriteria().andGreaterThanOrEqualTo("oid", 0); List<User>
		 * userList = userService.selectByExample(e);
		 * System.out.println("有索引--"+userList.size());
		 */
		return "/user/showInfo";
	}
	
	 /**
	  * 多线程 测试批量插入
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/user/saveByMultiThread",method= RequestMethod.GET)
	 @ApiOperation("多线程测试批量插入方法")
	 @ResponseBody
	 public String insertUserByMultithread(ModelMap modelMap){
		 
		 userService.threadInsertAll(100);
		 
	     return "/user/showInfo";
	 }
	 
	 


	/**
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/selectuser", method = RequestMethod.GET)
	@ApiOperation("测试 有索引的查询速度")
	@ResponseBody
	public String selectUserForUpdate(ModelMap modelMap) {
		List<User> userList = userService.selectAllForUpdate();
		System.out.println("有索引 forupdate--" + userList.size());
		return "/user/showInfo";
	}

	/**
	 * userCahce表，测试插入10w条数据
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/userCache/save", method = RequestMethod.GET)
	@ApiOperation("测试usercache 批量插入数据")
	@ResponseBody
	public Object insertCache(ModelMap modelMap) {
		/**
		 * 测试插入10w数据，用时，在服务器开启后第一次用时10s，后面用时2-4s
		 */
		List<UserCache> userCacheList = new ArrayList<UserCache>();
		for (int i = 0; i < 10000; i++) {
			UserCache userCache = new UserCache();
			userCache.setId(i);
			userCache.setName("关联查询" + i);
			userCache.setAge(i);
			userCacheList.add(userCache);
		}
		userCacheService.insertAll(userCacheList);

		/**
		 * 测试查询10w条数据，user_cache 未加索引
		 */
		/*
		 * Example e= new Example(UserCache.class);
		 * e.createCriteria().andGreaterThanOrEqualTo("id", 0); List<UserCache>
		 * userCacheList =userCacheService.selectByExample(e);
		 * System.out.println("无索引--"+userCacheList.size());
		 */
		return "userCacheList";
	}

	/**
	 * del 测试事务 具体看BaseServiceImpl
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */

	@RequestMapping(value = "/newb/del", method = RequestMethod.POST)
	@ApiOperation("测试通用mapper 的删除方法")
	@ResponseBody
	public String del(ModelMap modelMap) {
		int test = userService.delete(null);
		return test + "";
	}
	
	@Test
	private List<User> threadList(int page) {
//		int page=1;
		List<User> userList = new ArrayList<User>();
		for (int i =10000*page ; i < 10000*page+9999; i++) {
			User user = new User();
			user.setOid(i);
			user.setUsername(i + "");
			user.setPassword(i + "");
			userList.add(user);
		}
//		System.out.println("--- "+userList.size());
		return userList;
	}
}
