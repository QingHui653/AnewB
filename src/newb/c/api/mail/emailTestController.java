package newb.c.api.mail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import newb.c.backend.model.basemodel.User;
import newb.c.backend.service.ResultService;
import newb.c.backend.service.TestCacheService;
import newb.c.backend.service.UserService;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("view")
public class emailTestController {
	
	@Resource(name = "simpleEmailSendManagerImpl")
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
