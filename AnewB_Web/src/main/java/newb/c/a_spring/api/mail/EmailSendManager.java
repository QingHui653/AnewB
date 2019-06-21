package newb.c.a_spring.api.mail;

import javax.mail.MessagingException;

public interface EmailSendManager {
	
	/**
	 * 发送简单邮件
	 * @param simpleEmail 简单邮件详情
	 * @throws MessagingException 
	 */
	public void sendEmail(SimpleEmail simpleEmail) throws MessagingException;
	
}
