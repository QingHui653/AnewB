package newb.c.a_module.mq.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppConsumer {
	
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
		// 创建Session，参数解释：
        // 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，消息发送程序负责处理这个错误。
        // 第二个参数消息的确认模式：
        // AUTO_ACKNOWLEDGE ： 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。
        // CLIENT_ACKNOWLEDGE ： 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息）
        // DUPS_OK_ACKNOWLEDGE ： 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。

		Session session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//5.创建目标
		Destination destination =session.createQueue(queueName);
		
		//6.创建消费者
		MessageConsumer consumer =session.createConsumer(destination);
		
		
		
		//7.创建监听器
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				
				try {
					System.out.println("接受到"+ textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
