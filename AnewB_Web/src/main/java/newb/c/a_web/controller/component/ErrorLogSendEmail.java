package newb.c.a_web.controller.component;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import newb.c.a_spring.api.mail.EmailSendManager;
import newb.c.a_spring.api.mail.SimpleEmail;

//@Service
public class ErrorLogSendEmail implements HandlerExceptionResolver{
	
	@Resource(name = "simpleEmailSendManagerImpl")
	private EmailSendManager emailSendManager;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSubject("又报错了");
		Set<String> receivers = new HashSet<>();
		receivers.add("910944453@qq.com");
		simpleEmail.setToSet(receivers);
		simpleEmail.setHtml(false);
		ex.printStackTrace();
		String fullStackTrace =ExceptionUtils.getStackTrace(ex);
		simpleEmail.setContent("卧槽，工资还想不想要啦。\n  错误信息为 "+fullStackTrace);
		simpleEmail.setAttachment(false);

		try {
			emailSendManager.sendEmail(simpleEmail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return new ModelAndView("error");
	}

}
