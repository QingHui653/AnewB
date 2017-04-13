package newb.c.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import newb.c.backend.model.basemodel.User;
import newb.c.backend.service.ResultService;
import newb.c.backend.service.TestCacheService;
import newb.c.backend.service.UserService;
import newb.c.util.annotation.RequestLimit;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("view")
public class ViewController {
	
	@Resource
	private UserService userService;
	@Resource
	private ResultService resultService;
	@Resource
	private TestCacheService userCacheService;
	
	
	/**
	  *  返回中文字符串,需要在MVC中配置 不然会自动加引号
	  * @param modelMap
	  * @param userId
	  * @return
	  */
	 @RequestMapping(value="/string",method= RequestMethod.GET) //,produces ="text/html;charset=UTF-8" 返回UTF-格式
	 @ApiOperation("返回中文字符串，是否自动加引号 demo ")
	 @ResponseBody
	 @RequestLimit(count=3)
	 public String showUserByString(HttpServletRequest request){
	        String str="hello word 你好世界";
	        return str;
	 	}
	 
	 /**
	  * 返回JSON ,需要在MVC中配置
	  * @return
	  */
	 @RequestMapping(value="/json",method=RequestMethod.GET)
	 @ApiOperation("返回 json demo ")
	 @ResponseBody   //注释返回JSON 或 String 必须加此注解
	 public Object showUserInfoByJson(){
		 	Example userExample = new Example(User.class);
		 	userExample.createCriteria().andLessThan("oid", "5");
	        List<User> user = userService.selectByExample(userExample);
	        //使用gson返回json
//	        UserList userList = new UserList(user);
//	        String userJson= gson.toJson(userList);
	        Assert.notNull(user, "user 为 null");
	        boolean isNull=ObjectUtils.isEmpty(user);
	        System.out.println("Spring Untils 判断是否为null" +isNull);
	        return user;
	    }
	 
	 /**
	  * 返回json但未加@ResponseBody会自动跳转到
	  * @RequestMapping的value地址
	  * @return
	  */
	 @RequestMapping(value="/newbs2",method=RequestMethod.GET)   
	 @ApiOperation("跳转页面")
	 //@ResponseBody //未加会返回 user 至 newbs2.jsp 页面
	 public Object showUserInfoByMapping(){
	        List<User> user = userService.getUsers();
	        return user;
	    }
	 
	 @RequestMapping(value="/objectToView",method=RequestMethod.GET)   
	 @ApiOperation(" 跳转页面2")
	 //@ResponseBody //未加会返回 user 至 newbs2.jsp 页面
	 public Object showUserInfoByView(ModelMap model){
	        List<User> userList = userService.getUsers();
	        model.addAttribute("UserList", userList);
	        return "/newbs2";
	    }
	 
	/**
	 * 访问/user/newb/2 将user 绑定到modelMap 在JSP页面 使用 ${user.username}等访问
	 * 跳转到/user/showInfo // 测试页面在/web/mvc/indexMvc下，不知道为什么后台会有运行，但前台会自动刷新
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "获取用户列表 REST风格", notes = "")
	@RequestMapping(value = "/newb/{userId}", method = { RequestMethod.GET})
	public Object showUserInfoRest(ModelMap modelMap, @PathVariable int userId) {
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		return "/views/user/showInfo";
	}

	/**
	 * 访问/user/newb/2 将user 绑定到modelMap 在JSP页面 使用 ${user.username}等访问
	 * 跳转到/user/showInfo // 测试页面在/web/mvc/indexMvc下，不知道为什么后台会有运行，但前台会自动刷新
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "获取用户列表 http风格", notes = "")
	@ApiParam(value="userId",required=true)
	@RequestMapping(value = "/newb", method = { RequestMethod.GET})
	public Object showUserInfoHttp(ModelMap modelMap, int userId) {
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		return "/views/user/showInfo";
	}
	 
}
