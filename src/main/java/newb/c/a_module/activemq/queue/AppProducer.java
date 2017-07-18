package newb.c.a_module.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {
	
	private static final String url ="tcp://119.23.231.239:61616";
	
	private static final String queueName ="test_queue";
	
	public static void main(String[] args) throws JMSException {
		//1.创建连接工程
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		
		//2.创建连接
		Connection connection = connectionFactory.createConnection();
		
		//3.启动连接
		connection.start();
		
		//4.创建会话
		Session session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//5.创建目标
		Destination destination =session.createQueue(queueName);
		
		//6.创建生产者
		
		MessageProducer producer =session.createProducer(destination);
		
		// 设置持久化，DeliveryMode.PERSISTENT和DeliveryMode.NON_PERSISTENT
		//如果DeliveryMode没有设置或者设置为NON_PERSISTENT，那么重启MQ之后消息就会丢失。
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		for (int i = 0; i < 100; i++) {
			//7.创建消息
			TextMessage textMessage =session.createTextMessage(" test "+i);
			//8.发送消息
			producer.send(textMessage);
		}
		
		//9.关闭
		System.out.println("发送成功");
		connection.close();
		
	}
}
