package newb.c.a_spring.api.mail;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;

public class mailBeanTest {

	@Test
	public void sendSimpleEmail() throws MessagingException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "main/resources/mail/spring-mail.xml" });  
        context.start();
        
        MailSender mailBean=context.getBean("mailSender", MailSender.class); //  
        
        System.out.println(mailBean.toString());
	}
}
