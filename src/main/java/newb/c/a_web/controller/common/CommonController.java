package newb.c.a_web.controller.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonController {
	
	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	protected boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

	/**
	 * 获取当前请求session
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes())
				.getRequest();
		return request;
	}
	/**
	 * 获取当前请求session
	 * @return
	 */
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 获取当前请求全部
	 * @return
	 */
	public static Cookie[] getCookie() {
		return getHttpServletRequest().getCookies();
	}
}
