package newb.c.api.activeMQ.produce;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class ProducerServiceImpl{
	
	//ActiveMq
	@Autowired(required=false)
	private JmsTemplate jmsTemplate; 
	
	public void sendMessage(Destination destination, final String message) {   
        System.out.println("---------------生产者发送消息-----------------");   
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
        	
            public Message createMessage(Session session) throws JMSException {   
                return session.createTextMessage(message);     
            }   
            
        });   
    }
	
	public void sendObject(Destination destination, Object message) {   
        System.out.println("---------------生产者发送一个bean-----------------");   
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
        	
            public Message createMessage(Session session) throws JMSException {   
                return jmsTemplate.getMessageConverter().toMessage(message, session);     
            }   
            
        });   
    }  
}
