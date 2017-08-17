package newb.c.webmodule.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("--------初始化自定义的Listener");
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
