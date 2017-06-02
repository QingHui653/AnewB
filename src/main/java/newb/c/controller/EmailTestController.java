package newb.c.controller;

import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import newb.c.api.mail.EmailSendManager;
import newb.c.api.mail.SimpleEmail;

@Controller
@RequestMapping("view")
public class EmailTestController {
	
	@Autowired(required=false)
	@Qualifier(value="simpleEmailSendManagerImpl")
	private EmailSendManager emailSendManager;
	
	/**
	 * 发送简单邮件
	 * @throws MessagingException
	 */
	@RequestMapping(value="sendSimpleEmail",method=RequestMethod.GET)
	public void sendSimpleEmail() throws MessagingException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSubject("测试在Spring中发送邮件");
		Set<String> receivers = new HashSet<>();
		receivers.add("910944454@qq.com");
		simpleEmail.setToSet(receivers);
		simpleEmail.setHtml(false);
		simpleEmail.setContent("Netty是由JBOSS提供的一个java开源框架。Netty提供异步的、"+ "事件驱动的网络应用程序框架和工具，用以快速开发高性能、高可靠性的网络服务器和客户端程序。");
		simpleEmail.setAttachment(false);

		emailSendManager.sendEmail(simpleEmail);
		
		System.out.println("       发送简单邮件成功");
	}
	 
}
