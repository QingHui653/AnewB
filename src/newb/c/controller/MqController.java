package newb.c.controller;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import newb.c.controller.component.ProducerServiceImpl;

@Controller
@RequestMapping("mq")
public class MqController {
	
	private static final Logger logger =LoggerFactory.getLogger(MqController.class); 
	
	//ActiveMQ
	
	@Autowired(required=false)
	private ProducerServiceImpl producerServiceImpl;
	@Autowired(required=false)
	private Destination destination;
	
	@RequestMapping(value="/sendmq",method=RequestMethod.GET)
	@ApiOperation("测试mq发送消息")
	@ResponseBody
	public String sendmq() {
//		ActiveMq
		producerServiceImpl.sendMessage(destination, "HHHHHworld");
		return "OK";
	}
}
