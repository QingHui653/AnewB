package newb.c.a_web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName LoginController
 * @author yuan
 * @version 1.0
 */
@Controller
public class LoginController {

	/**
	 *  访问/user/newb/2
	 *  将user 绑定到modelMap
	 *  在JSP页面 使用 ${user.username}等访问
	 *  跳转到/user/showInfo
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation("使用shiro  进行登录调用")
	public Object execute(ModelAndView mv ,HttpServletRequest request,HttpServletResponse response, String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		// 记录该令牌
		token.setRememberMe(false);
		// subject权限对象
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		String exceptionClassName = (String) request
				.getAttribute("shiroLoginFailure");
		//判断是否已登录
		if (subject.isAuthenticated()){
			subject.logout();
		}
		try {
			// 提交申请，验证能不能通过，也回调reaml里的doGetAuthenticationInfo验证是否通过
			subject.login(token);
			System.out.println(exceptionClassName);
		} catch (UnknownAccountException ex) {// 用户名没有找到
			mv.addObject("msg", "用户未找到");
//			ex.printStackTrace();
		} catch (IncorrectCredentialsException ex) {// 用户名密码不匹配
			mv.addObject("msg", "密码不正确");
//			map.put("msg", "密码不正确");
//			ex.printStackTrace();
		} catch (AuthenticationException e) {// 其他的登录错误
			mv.addObject("msg", "其他错误");
//			e.printStackTrace();
		} catch (Exception e) {
			mv.addObject("msg", "登录异常");
//			e.printStackTrace();
		}

		// 验证是否成功登录的方法
		if (subject.isAuthenticated()) {
//			mv.setViewName("index");
			return "forward:/user/newb/2";
		}else{
//			mv.setViewName("redirect:/login.jsp");
			mv.setViewName("redirect:/views/jsp/login.jsp"); //此处偷懒，一般是ajax请求，或重定向时将失败传回
		}
//		return new ModelAndView("redirect:/login.jsp");
		return mv;
	}

	// 退出
	@RequestMapping(value="/logout",method={RequestMethod.GET})
	@ApiOperation("使用shiro  进行退出")
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
}
