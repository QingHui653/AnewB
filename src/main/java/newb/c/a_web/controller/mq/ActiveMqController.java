package newb.c.a_web.controller.mq;

import javax.jms.Destination;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import newb.c.a_spring.api.mq.activemq.producer.ProducerServiceImpl;
import newb.c.a_spring.backend.sql.model.basemodel.User;

/**
 * topic   |queue
 * 发布订阅|点对点
 * 不落地无状态|默认保存在$AMQ_HOME\data\kr-store\data或DB
 * 并不保证publisher发布的每条数据，Subscriber都能接受到。|Queue保证每条数据都能被receiver接收
 * 一般来说publisher发布消息到某一个topic时，只有正在监听该topic地址的sub能够接收到消息；如果没有sub在监听，该topic就丢失了。|Sender发送消息到目标Queue，receiver可以异步接收这个Queue上的消息。Queue上的消息如果暂时没有receiver来取，也不会丢失。
 * 对多的消息发布接收策略，监听同一个topic地址的多个sub都能收到publisher发送的消息。Sub接收完通知mq服务器|一对一的消息发布接收策略，一个sender发送的消息，只能有一个receiver接收。receiver接收完后，通知mq服务器已接收，mq服务器对queue里的消息采取删除或其他操作。
 */

/**
 * java连接端口为61616 web admn端口为8161
 */
@Controller
@RequestMapping("resources/mq")
@Slf4j
public class ActiveMqController {
	@Autowired(required=false)
	private ProducerServiceImpl producerServiceImpl;
	// queue 点对点模式
	// topic 广播模式
	private Destination queueDestination =new ActiveMQQueue("queue string");

	private Destination queueBeanDestination =new ActiveMQQueue("queue object");

	private Destination topicDestination =new ActiveMQTopic("topic string");

	private Destination topicBeanDestination =new ActiveMQTopic("topic object");

	@RequestMapping(value="/sendQueueMq  queue",method=RequestMethod.GET)
	@ApiOperation("测试sendQueueMq发送消息 queue string")
	@ResponseBody
	public String sendQueueMq() {
		producerServiceImpl.sendQueueMessage(queueDestination, "HHHHHworld HHHHHworld queue->queue");
		return "OK";
	}

	@RequestMapping(value="/sendObjectMq queue object",method=RequestMethod.GET)
	@ApiOperation("测试sendObjectMq发送消息 queue object")
	@ResponseBody
	public String sendObjectMq() {
		User user =new User(33, "queue", "queue");
		producerServiceImpl.sendQueueObject(queueBeanDestination, user);
		return "OK";
	}

	@RequestMapping(value="/sendTopicMq topic",method=RequestMethod.GET)
	@ApiOperation("测试sendTopicMq发送消息 topic string")
	@ResponseBody
	public String sendTopicMq() {
		producerServiceImpl.sendTopicMessage(topicDestination, "HHHHHworld");
		return "OK";
	}

	@RequestMapping(value="/sendObjectMq topic object",method=RequestMethod.GET)
	@ApiOperation("测试sendObjectMq发送消息 topic object")
	@ResponseBody
	public String sendTopicObjectMq() {
		User user =new User(33, "topic", "topic");
		producerServiceImpl.sendTopicObject(topicBeanDestination, user);
		return "OK";
	}
}
