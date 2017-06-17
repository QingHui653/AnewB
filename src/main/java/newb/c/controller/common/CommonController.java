package newb.c.controller.common;

import javax.servlet.http.HttpServletRequest;

public class CommonController {
	
	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	protected boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}
}
