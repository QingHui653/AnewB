package newb.c.a_web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import newb.c.a_web.controller.component.AyscTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import newb.c.a_spring.backend.sql.model.basemodel.User;
import newb.c.a_spring.backend.sql.service.UserService;
import newb.c.util.annotation.RequestLimit;
import tk.mybatis.mapper.entity.Example;

/**
 * springmvc页面的跳转 数据的返回 范例
 * 
 * @author woshizbh
 *
 */
@Controller
@RequestMapping("mvc")
public class MvcController {

	@Autowired
	private UserService userService;
	@Autowired
	private AyscTask ayscTask;

	/**
	 * 返回中文字符串,需要在MVC中配置 不然会自动加引号
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/string", method = RequestMethod.GET) // ,produces
																	// ="text/html;charset=UTF-8"
																	// 返回UTF-格式
	@ApiOperation("返回中文字符串，是否自动加引号 demo ")
	@ResponseBody
	@RequestLimit(count = 3)
	public String showUserByString(HttpServletRequest request) {
		// 获取request接受的全部参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			Object paraName = (Object) enu.nextElement();
			paramMap.put((String) paraName, request.getParameter((String) paraName));
			System.out.println(paraName + ": " + request.getParameter((String) paraName));
		}
		String str = "hello word 你好世界";
		return str;
	}

	/**
	 * 返回JSON ,需要在MVC中配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ApiOperation("返回 json demo ")
	@ResponseBody // 注释返回JSON 或 String 必须加此注解
	public Object showUserInfoByJson() {
		Example userExample = new Example(User.class);
		userExample.createCriteria().andLessThan("oid", "5");
		List<User> user = userService.selectByExample(userExample);
		// 使用gson返回json
		// UserList userList = new UserList(user);
		// String userJson= gson.toJson(userList);
		Assert.notNull(user, "user 为 null");
		boolean isNull = ObjectUtils.isEmpty(user);
		System.out.println("Spring Untils 判断是否为null" + isNull);
		return user;
	}

	/**
	 * 返回json但未加@ResponseBody会自动跳转到
	 * 
	 * @RequestMapping的value地址
	 * @return
	 */
	@RequestMapping(value = "/newbs2", method = RequestMethod.GET)
	@ApiOperation("跳转页面")
	// @ResponseBody //未加会返回 user 至 newbs2.jsp 页面
	public Object showUserInfoByMapping() {
		List<User> user = userService.getUsers();
		return user;
	}

	@RequestMapping(value = "/objectToView", method = RequestMethod.GET)
	@ApiOperation(" 跳转页面2")
	// @ResponseBody //未加会返回 user 至 newbs2.jsp 页面
	public Object showUserInfoByView(ModelMap model) {
		List<User> userList = userService.getUsers();
		model.addAttribute("UserList", userList);
		return "/views/jsp/newb";
	}

	/**
	 * 访问/user/newb/2 将user 绑定到modelMap 在JSP页面 使用 ${user.username}等访问
	 * 跳转到/user/showInfo // 测试页面在/web/mvc/indexMvc下，不知道为什么后台会有运行，但前台会自动刷新
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "获取用户列表 REST风格", notes = "")
	@RequestMapping(value = "/newb/{userId}", method = { RequestMethod.GET })
	public Object showUserInfoRest(ModelMap modelMap, @PathVariable int userId) {
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		return "/views/jsp/user/showInfo";
	}

	/**
	 * 访问/user/newb/2 将user 绑定到modelMap 在JSP页面 使用 ${user.username}等访问
	 * 跳转到/user/showInfo // 测试页面在/web/mvc/indexMvc下，不知道为什么后台会有运行，但前台会自动刷新
	 * 
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "获取用户列表 http风格", notes = "")
	@ApiParam(value = "userId", required = true)
	@RequestMapping(value = "/newb", method = { RequestMethod.GET })
	public Object showUserInfoHttp(ModelMap modelMap, int userId) {
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		return "/views/jsp/user/showInfo";
	}

	/**
	 * springmvc 返回异步任务
	 * 
	 * @throws Exception
	 */
	@ApiOperation("调用异步任务")
	@GetMapping("/asycTest")
	public void asycTest() throws Exception {
		long start = System.currentTimeMillis();
		Future<String> task1 = ayscTask.doTaskOne();
		Future<String> task2 = ayscTask.doTaskTwo();
		Future<String> task3 = ayscTask.doTaskThree();
		while (true) {
			/* 看时间好像是 spring 开了三个线程去做 */
			if (task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				System.out.println(task1.get());
				System.out.println(task2.get());
				System.out.println(task3.get());
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}


	/**
	 * 使用forward 使之 重新进入另一个controller /user/newbs 转发
	 */
	@RequestMapping(value = "/forward", method = RequestMethod.POST)
	@ApiOperation("登录后 转发到新的controller")
	public String loginFor(ModelMap modelMap, String username, String password) {
		int userId = 2;
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		// return "redi:/user/newbs";
		return "forward:/views/jsp/user/newbs";
	}

	/**
	 * forward与redirect的区别 1.forward地址栏不会改变 2.forward会共享上个controller的request数据
	 * 3.forward主要用于跳转到其他模块，redirect用于注销或session过期，跳转到其他网站
	 * 4.forward效率比redirect效率高
	 */

	/**
	 * 使用forward 使之 重新进入另一个controller /user/newbs 转发
	 */
	@RequestMapping(value = "/redirect", method = RequestMethod.POST)
	@ApiOperation("登录后 重定向到新的页面")
	public String redirect(ModelMap modelMap, String username, String password) {
		int userId = 2;
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		// return "redi:/user/newbs";
		return "redirect:/views/jsp/user/newbs";
	}
	
}
