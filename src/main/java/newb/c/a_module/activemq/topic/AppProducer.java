package newb.c.a_module.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {
	
	private static final String url ="tcp://119.23.231.239:61616";
	
	private static final String topicName ="test_topic";
	
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
		Destination destination =session.createTopic(topicName);
		
		//6.创建生产者
		
		MessageProducer producer =session.createProducer(destination);
		
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
