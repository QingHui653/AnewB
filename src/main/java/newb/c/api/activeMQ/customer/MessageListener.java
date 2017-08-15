package newb.c.api.activeMQ.customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import newb.c.backend.model.basemodel.User;

/**
 * 使用注解监听 jms
 * @author woshizbh
 *
 */
@Component
public class MessageListener {

	@Autowired(required=false)
	private JmsTemplate jmsTemplate;

	@JmsListener(destination="queue",concurrency="10-15")//监听并行区间范围 最小10个 最大15个
	public void queueListener(Message message,Session session) throws JMSException {
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到queue一个纯文本消息。");
        System.out.println(session);
        //消息确认
        message.acknowledge();
        System.out.println("消息内容是：" + textMsg.getText());
    }

	@JmsListener(destination="topic")
	public void queueTopicListener(Message message,Session session) throws JMSException {
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到queue topic一个纯文本消息。");
        System.out.println(session);
        //消息确认
        message.acknowledge();
        System.out.println("消息内容是：" + textMsg.getText());
    }

	@JmsListener(destination="topic",containerFactory="topicListener")
	public void topicTopicListener(Message message,Session session) throws JMSException {
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到topic  topic一个纯文本消息。");
        System.out.println(session);
        //消息确认
        message.acknowledge();
        System.out.println("消息内容是：" + textMsg.getText());
    }

	@JmsListener(destination="object")
	public void beanListener(Message message,Session session) throws JMSException {
        Object user =(Object) jmsTemplate.getMessageConverter().fromMessage(message);
        System.out.println("接收到object信息"+user.toString());
        System.out.println(session);
        //消息确认
        message.acknowledge();
    }

}
