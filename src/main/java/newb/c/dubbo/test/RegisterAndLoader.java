package newb.c.dubbo.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import newb.c.backend.model.basemodel.User;
import newb.c.dubbo.DemoService;

public class RegisterAndLoader {  
	  
	@SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
    	
    	ClassPathXmlApplicationContext RegistContext = new ClassPathXmlApplicationContext(new String[] { "main/resources/dubbo/springhighGrade-dubbo.xml" });  
    	RegistContext.start();
    	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "main/resources/dubbo/springhighGradeT-dubbo-test.xml" });  
        context.start(); 
        
        DemoService demoService = (DemoService) context.getBean("demoService"); //  
        String hello = demoService.sayHello("tom"); // 
        System.out.println(hello); //   
  
        List<User> list = demoService.getUsers();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                System.out.println(list.get(i));  
            }  
        }  
//        System.out.println(demoService.hehe());  
//        System.in.read();  
    }  
  
} 
