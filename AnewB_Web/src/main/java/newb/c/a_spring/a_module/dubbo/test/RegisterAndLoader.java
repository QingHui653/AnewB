package newb.c.a_spring.a_module.dubbo.test;

import java.util.List;

import newb.c.a_spring.a_module.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import newb.c.a_spring.backend.sql.model.basemodel.User;

public class RegisterAndLoader {  
	  
	@SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
    	
    	ClassPathXmlApplicationContext RegistContext = new ClassPathXmlApplicationContext(new String[] { "config/dubbo/spring-dubbo.xml" });
    	RegistContext.start();
    	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "config/dubbo/spring-dubbo-test.xml" });
        context.start(); 
        
        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("tom");
        System.out.println(hello);
  
        List<User> list = demoService.getUsers();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                System.out.println(list.get(i));  
            }  
        }


        if(context == null){
            System.out.println("no spring context start,quit.");
            return;
        }
        if(true) {
            while(true){
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
} 
