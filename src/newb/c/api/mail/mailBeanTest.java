package newb.c.api.mail;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class mailBeanTest {

	@Test
	public void sendSimpleEmail() throws MessagingException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "main/resources/mail/spring-mail.xml" });  
        context.start();
        
        MailSender mailBean=context.getBean("mailSender", MailSender.class); //  
        
        System.out.println(mailBean.toString());
	}
}
