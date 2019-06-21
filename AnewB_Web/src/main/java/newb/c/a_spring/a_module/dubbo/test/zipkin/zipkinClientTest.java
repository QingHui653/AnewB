package newb.c.a_spring.a_module.dubbo.test.zipkin;

import newb.c.a_spring.a_module.dubbo.ZipkinRefService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class zipkinClientTest {
  
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/dubbo/zipkin/spring-dubbo-zipkin-client.xml");
        context.start();

        ZipkinRefService zipkinRefService = (ZipkinRefService) context.getBean("zipkinRefService");
        System.out.println(zipkinRefService.greeting(" 从 zipkinClient 进入"));
        System.in.read();
    }  
  
} 
