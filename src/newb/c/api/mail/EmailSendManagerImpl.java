package newb.c.api.mail;

import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("simpleEmailSendManagerImpl")
public class EmailSendManagerImpl implements EmailSendManager {
	
	@Value("${mailserver.username}")
//	@Value("#{configProperties['mailserver.username']}") 
	private String fromUser;  //发送者
	
	@Resource
	private JavaMailSender mailSender;
	
	private static final Logger logger= LoggerFactory.getLogger(EmailSendManagerImpl.class); 
	@Override
	public void sendEmail(SimpleEmail simpleEmail) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, simpleEmail.isAttachment());
		
		/**
		 * 添加发送者
		 */
		logger.debug("邮件发送方为"+fromUser);
		helper.setFrom(fromUser);
		
		Set<String> toSet =simpleEmail.getToSet();
		/**
		 * 添加接收者
		 */
		helper.setTo(toSet.toArray(new String[toSet.size()]));
		
		/**
		 * 添加主题
		 */
		helper.setSubject(simpleEmail.getSubject());
		/**
		 * 添加正文
		 */
		helper.setText(simpleEmail.getContent(),simpleEmail.isHtml());
		
		/**
		 * 添加附件
		 */
		if(simpleEmail.isAttachment()){
			Map<String, File> attachments = simpleEmail.getAttachments();
			
			if(attachments != null){
				for(Map.Entry<String, File> attach : attachments.entrySet()){
					helper.addAttachment(attach.getKey(), attach.getValue());
				}
				
			}

		}
		
		mailSender.send(message);  //发送
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	
	
}

