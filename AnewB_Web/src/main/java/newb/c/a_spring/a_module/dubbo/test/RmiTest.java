package newb.c.a_spring.a_module.dubbo.test;

import java.util.List;  

import org.springframework.context.support.ClassPathXmlApplicationContext;

import newb.c.a_spring.a_module.dubbo.RmiDemoService;
import newb.c.a_spring.backend.sql.model.basemodel.User;

  
public class RmiTest {  
  
    public static void main(String[] args) throws Exception {  
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "main/resources/rmi/springT-rmi-test.xml" });  
        context.start();  
        System.out.println("加载Spring容器,并初始化RMI客户端");
        RmiDemoService rmiDemoService = (RmiDemoService) context.getBean("rmiService"); //
        String hello =  rmiDemoService.sayHello("hello");
        System.out.println(hello);
        
        List<User> list = rmiDemoService.getUsers();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                System.out.println(list.get(i));  
            }  
        } 
    }  
  
} 
