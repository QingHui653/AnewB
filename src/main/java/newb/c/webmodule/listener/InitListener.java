package newb.c.webmodule.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.catalina.core.ApplicationContextFacade;

public class InitListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("--------初始化自定义的Listener 开始");
		Enumeration<String> enu= sce.getServletContext().getInitParameterNames();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取<context-param>的值
		while (enu.hasMoreElements()) {
			Object paraName = (Object) enu.nextElement();
			paramMap.put((String) paraName, sce.getServletContext().getInitParameter((String) paraName));
			System.out.println(paraName + ": " + sce.getServletContext().getInitParameter((String) paraName));
		}
		Enumeration<String> attrs= sce.getServletContext().getAttributeNames();
		System.out.println("--------初始化自定义的Listener 结束");
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
