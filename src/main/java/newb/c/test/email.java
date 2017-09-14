package newb.c.test;
import java.util.Random;

import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class email {
	@Test
	public void email() {
		String result = "success";
		String verifyCode="";
		SimpleEmail emails = new SimpleEmail();  
        try{
        	emails.setSSL(true);
            emails.setHostName("smtp.ivyb2b.com");  
            emails.setSmtpPort(465);
            emails.setAuthentication("test@ivyb2b.com","ivyb2b@2016"); 
            emails.addTo("910944453@qq.com", "me"); 
            emails.setFrom("test@ivyb2b.com", "常青藤"); 
            emails.setSubject("更换邮箱校验");  
            emails.setCharset("UTF-8"); 
            emails.setMsg("邮箱校验码为：2017090791624");  
            emails.send();  
        } catch (Exception e){   
        	result="emailError";
            e.printStackTrace(); 
        }
	}
}
