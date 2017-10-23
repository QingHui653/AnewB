package newb.c.webmodule.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("--------初始化自定义的Listener 开始");
		Enumeration<String> enu= event.getServletContext().getInitParameterNames();
		//tomcat下的ApplicationContextFacade
		ServletContext context = event.getServletContext();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取<context-param>的值
		while (enu.hasMoreElements()) {
			Object paraName = (Object) enu.nextElement();
			paramMap.put((String) paraName, event.getServletContext().getInitParameter((String) paraName));
			System.out.println(paraName + ": " + event.getServletContext().getInitParameter((String) paraName));
		}
		Enumeration<String> attrs= event.getServletContext().getAttributeNames();
		System.out.println("--------初始化自定义的Listener 结束");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
