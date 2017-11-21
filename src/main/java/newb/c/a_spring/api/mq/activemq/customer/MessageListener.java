package newb.c.a_spring.api.mq.activemq.customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;

/**
 * 使用注解监听 jms
 * @author woshizbh
 *
 */
@Service
public class MessageListener {

    //ActiveMq
    @Autowired(required=false)
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Autowired(required=false)
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

	@JmsListener(destination="queue string",concurrency="10-15")//监听并行区间范围 最小10个 最大15个
	public void queueListener(Message message,Session session) throws JMSException {
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到queue一个纯文本消息。");
        System.out.println(session);
        //消息确认
        message.acknowledge();
        System.out.println("消息内容是：" + textMsg.getText());
    }

    @JmsListener(destination="queue object")
    public void beanListener(Message message,Session session) throws JMSException {
        Object user =(Object) jmsQueueTemplate.getMessageConverter().fromMessage(message);
        System.out.println("接收到queue object信息"+user.toString());
        System.out.println(session);
        //消息确认
        message.acknowledge();
    }

	@JmsListener(destination="topic string",containerFactory = "topicListener")
	public void queueTopicListener(Message message,Session session) throws JMSException {
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到queue topic一个纯文本消息。");
        System.out.println(session);
        //消息确认
        message.acknowledge();
        System.out.println("消息内容是：" + textMsg.getText());
    }

	@JmsListener(destination="topic object",containerFactory="topicListener")
	public void topicTopicListener(Message message,Session session) throws JMSException {
        Object user =(Object) jmsTopicTemplate.getMessageConverter().fromMessage(message);
        System.out.println("接收到topic object信息"+user.toString());
        System.out.println(session);
        //消息确认
        message.acknowledge();
    }

}
