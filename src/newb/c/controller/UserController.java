package newb.c.controller;

import java.util.List;

import newb.c.model.User;
import newb.c.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@RequestMapping("/newb/{userId}") 
	 public String showUserInfo(ModelMap modelMap, @PathVariable int userId){  
	        User user = userService.getUserById(userId);
	        modelMap.addAttribute("user", user);  
	        return "/user/showInfo";  
	    } 
	 
	@RequestMapping("/newbb") //,produces ="text/html;charset=UTF-8"
	 public @ResponseBody String showUser(@RequestParam("userid") int userId){  
	        User user = userService.getUserById(userId);
//	        modelMap.addAttribute("user", user);
	        String str="hello word 你好世界";
	        return str;  
	    }
	 
	 @RequestMapping("/newbs")  
	    public @ResponseBody Object showUserInfos(){  
	        List<User> user = userService.getUsers();  
	        return user;  
	    } 
	 
	 @RequestMapping("/login") 
	 public String login(ModelMap modelMap,@RequestParam("username") String username,@RequestParam("password") String password){  
		 	int userId=2;
		 	User user = userService.getUserById(userId);
	        modelMap.addAttribute("user", user);  
	        return "/admin/showInfo";  
	    }
	 
}
