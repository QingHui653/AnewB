package newb.c.controller;

import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import newb.c.api.mail.EmailSendManager;
import newb.c.api.mail.SimpleEmail;

//@Controller
@RequestMapping("/mail")
public class MailController {
	/*@Autowired(required=false)
	@Qualifier(value= "simpleEmailSendManagerImpl")*/
	private EmailSendManager emailSendManager;
	
	/**
	 * 发送简单邮件
	 * @throws MessagingException
	 */
	@RequestMapping(value="sendSimpleEmail",method=RequestMethod.GET)
	@ApiOperation("发送 邮件")
	public void sendSimpleEmail() throws MessagingException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSubject("测试在项目中发送邮件");
		Set<String> receivers = new HashSet<>();
		receivers.add("910944453@qq.com");
		simpleEmail.setToSet(receivers);
		simpleEmail.setHtml(false);
		simpleEmail.setContent("wowowowo擦，别被网易屏蔽");
		simpleEmail.setAttachment(false);

		emailSendManager.sendEmail(simpleEmail);
		
		System.out.println("       发送简单邮件成功");
	}
}
