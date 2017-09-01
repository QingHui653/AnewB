package newb.c.controller;

import javax.jms.Destination;

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
import newb.c.api.activeMQ.produce.ProducerServiceImpl;
import newb.c.backend.model.basemodel.User;

@Controller
@RequestMapping("mq")
public class ActiveMqController {

	private static final Logger logger =LoggerFactory.getLogger(ActiveMqController.class);

	//ActiveMQ
	@Autowired(required=false)
	private JmsTemplate jmsTemplate;
	
	@Autowired(required=false)
	private ProducerServiceImpl producerServiceImpl;

	private Destination queueDestination =new ActiveMQQueue("queue");

	private Destination topicDestination =new ActiveMQTopic("topic");

	private Destination beanDestination =new ActiveMQQueue("object");

	@RequestMapping(value="/sendQueueMq  queue",method=RequestMethod.GET)
	@ApiOperation("测试sendQueueMq发送消息")
	@ResponseBody
	public String sendQueueMq() {
//		ActiveMq
		producerServiceImpl.sendMessage(queueDestination, "HHHHHworld HHHHHworld queue->queue");
		producerServiceImpl.sendMessage(new ActiveMQQueue("topic"), "HHHHHworld  queue->topic");
		return "OK";
	}

	@RequestMapping(value="/sendQueueMq  topic",method=RequestMethod.GET)
	@ApiOperation("测试sendQueueMq发送消息")
	@ResponseBody
	public String sendQueueMqToTopic() {
//		ActiveMq
		producerServiceImpl.sendMessage(new ActiveMQQueue("topic"), "HHHHHworld topic");
		return "OK";
	}

	@RequestMapping(value="/sendTopicMq topic",method=RequestMethod.GET)
	@ApiOperation("测试sendTopicMq发送消息")
	@ResponseBody
	public String sendTopicMq() {
//		ActiveMq
		producerServiceImpl.sendMessage(topicDestination, "HHHHHworld");
		return "OK";
	}

	@RequestMapping(value="/sendObjectMq queue object",method=RequestMethod.GET)
	@ApiOperation("测试sendObjectMq发送消息")
	@ResponseBody
	public String sendObjectMq() {
//		ActiveMq
		User user =new User(33, "三十三", "三十三");
		producerServiceImpl.sendObject(beanDestination, user);
		return "OK";
	}
}
