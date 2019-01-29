package newb.c.a_spring.a_module.dubbo.test;

import java.util.List;

import newb.c.a_spring.a_module.dubbo.RestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import newb.c.a_spring.backend.sql.model.basemodel.User;

public class dubboRestTest {  
  
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext RegistContext = new ClassPathXmlApplicationContext(new String[] { "config/dubbo/spring-dubboxRest.xml" });
        RegistContext.start();

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "config/dubbo/spring-dubbox-test.xml" });
        context.start();  
  
        RestService restService = (RestService) context.getBean("restService"); //
        String hello = restService.Hello();
        System.out.println(hello); //   
  
        List<User> list = restService.getUsers();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                System.out.println(list.get(i));  
            }  
        }  
        System.out.println(restService.Hello());  
        System.in.read();  
    }  
  
} 
