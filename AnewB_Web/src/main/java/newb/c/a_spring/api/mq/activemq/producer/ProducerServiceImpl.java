package newb.c.a_spring.api.mq.activemq.producer;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl{
	
	//ActiveMq
	@Autowired(required=false)
    @Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

    @Autowired(required=false)
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;
	
	public void sendQueueMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息 Queue-----------------");
        System.out.println("---------------生产者发了一个Queue 消息：" + message);
        jmsQueueTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {   
                return session.createTextMessage(message);     
            }   
            
        });   
    }
	
	public void sendQueueObject(Destination destination, Object message) {
        System.out.println("---------------生产者发送一个Queue bean-----------------");
        System.out.println("---------------生产者发了一个Queue 消息：" + message);
        jmsQueueTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {   
                return jmsQueueTemplate.getMessageConverter().toMessage(message, session);
            }   
            
        });
    }

    public void sendTopicMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息Topic -----------------");
        System.out.println("---------------生产者发了一个Topic  消息：" + message);
        jmsTopicTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }

        });
    }

    public void sendTopicObject(Destination destination, Object message) {
        System.out.println("---------------生产者发送一个Topic  bean-----------------");
        System.out.println("---------------生产者发了一个Topic  消息：" + message);
        jmsTopicTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return jmsTopicTemplate.getMessageConverter().toMessage(message, session);
            }

        });
    }
}
