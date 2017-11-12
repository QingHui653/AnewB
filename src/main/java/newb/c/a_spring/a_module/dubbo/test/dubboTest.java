package newb.c.a_spring.a_module.dubbo.test;

import java.util.List;

import newb.c.a_spring.a_module.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import newb.c.a_spring.backend.sql.model.basemodel.User;

  
public class dubboTest {  
  
	@SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {  
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "main/resources/dubbo/spring-dubbo-test.xml" });  
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
