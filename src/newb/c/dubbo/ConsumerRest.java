package newb.c.dubbo;

import java.util.List;  

import org.springframework.context.support.ClassPathXmlApplicationContext;  

import newb.c.model.User;
  
public class ConsumerRest {  
  
    public static void main(String[] args) throws Exception {  
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "springhighGradeT-dubbox-test.xml" });  
        context.start();  
  
        RestService restService = (RestService) context.getBean("restService"); //  
        String hello = restService.Hello();
        System.out.println(hello); //   
  
//        List<User> list = restService.getUsers();  
//        if (list != null && list.size() > 0) {  
//            for (int i = 0; i < list.size(); i++) {  
//                System.out.println(list.get(i));  
//            }  
//        }  
//        System.out.println(demoService.hehe());  
//        System.in.read();  
    }  
  
} 
