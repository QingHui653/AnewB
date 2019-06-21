package newb.c.a_spring.api.mail;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:main/resources/mail/spring-mail.xml", "classpath:main/resources/mail/springmvcTest.xml" })
public class TestSendEmailUnit {

	@Resource(name = "simpleEmailSendManagerImpl")
	private EmailSendManager emailSendManager;

	/**
	 * 发送简单邮件
	 * @throws MessagingException
	 */
	public void sendSimpleEmail() throws MessagingException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSubject("测试在Spring中发送邮件");

		Set<String> receivers = new HashSet<>();
		receivers.add("wanfenglin596@163.com");
		simpleEmail.setToSet(receivers);

		simpleEmail.setHtml(false);
		simpleEmail.setContent("Netty是由JBOSS提供的一个java开源框架。Netty提供异步的、"
				+ "事件驱动的网络应用程序框架和工具，用以快速开发高性能、高可靠性的网络服务器和客户端程序。");

		simpleEmail.setAttachment(false);

		emailSendManager.sendEmail(simpleEmail);
		
		System.out.println("发送简单邮件成功");
	}
	
	
	/**
     * 发送带附件的邮件
     * @throws MessagingException
     */
    public void sendEmailWithAttachment() throws MessagingException {
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setSubject("测试在Spring中发送带有附件的邮件");
 
        Set<String> receivers = new HashSet<>();
        receivers.add("910944454@qq.com");
        simpleEmail.setToSet(receivers);
 
        simpleEmail.setHtml(false);
        simpleEmail.setContent(
                "Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者规模的网站中的所有动作流数据。 "
                + "这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 "
                + "这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 "
                + "对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。"
                + "Kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群来提供实时的消费。");
 
        simpleEmail.setAttachment(true);
        
        Map<String, File> attachments = new HashMap<>();  //附件集合
        /**
         * web项目中使用request.getSession().getServletContext().getRealPath("/uploads")获取路径
         */
        File sockjs = new File("C://Users//woshizbh//Desktop//1.txt");
        attachments.put(sockjs.getName(), sockjs);
        
        File stomp = new File("C://Users//woshizbh//Desktop//2.txt");
        attachments.put(stomp.getName(), stomp);
        
        File jquery = new File("C://Users//woshizbh//Desktop//3.txt");
        attachments.put(jquery.getName(), jquery);
 
        simpleEmail.setAttachments(attachments);
        
        emailSendManager.sendEmail(simpleEmail);
        
        System.out.println("发送附件邮件成功");
    }
    
    
    /**
     * 发送HTML格式的邮件
     * @throws MessagingException
     */
    @Test
    public void sendHTMLEmail() throws MessagingException {
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setSubject("测试在Spring中发送带HTML格式的邮件");
 
        Set<String> receivers = new HashSet<>();
        receivers.add("910944454@qq.com");
        simpleEmail.setToSet(receivers);
 
        simpleEmail.setHtml(true);
        simpleEmail.setContent("<html><head><meta http-equiv=\"Content-Type\" "
                + "content=\"text/html; charset=UTF-8\"></head>"
                + "<body><div align=\"center\" style=\"color:#F00\">"
                + "<h2>测试在Spring中发送带HTML格式的邮件</h2></div></body></html>");
 
        simpleEmail.setAttachment(false);
 
        emailSendManager.sendEmail(simpleEmail);
        
        System.out.println("发送HTML邮件成功");
    }
}
