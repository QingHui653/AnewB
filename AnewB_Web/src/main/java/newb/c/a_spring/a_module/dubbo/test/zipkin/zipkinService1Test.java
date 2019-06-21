package newb.c.a_spring.a_module.dubbo.test.zipkin;

import newb.c.a_spring.a_module.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class zipkinService1Test {
  
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/dubbo/zipkin/spring-dubbo-zipkin-service.xml");
        context.start(); 
  
        DemoService demoService = (DemoService) context.getBean("demoService"); //
        System.out.println(demoService.sayHello("111"));
        System.in.read();
    }  
  
} 
