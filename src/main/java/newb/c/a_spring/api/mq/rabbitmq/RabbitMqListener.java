package newb.c.a_spring.api.mq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import newb.c.a_spring.backend.sql.dao.MqStudentMapper;
import newb.c.a_spring.backend.sql.dao.MqTeachMapper;
import newb.c.a_spring.backend.sql.model.MqTeachDTO;
import newb.c.a_spring.backend.sql.model.basemodel.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class RabbitMqListener  {

	@Autowired
	private MqTeachMapper mqTeachMapper;
	@Autowired
	private MqStudentMapper mqStudentMapper;

	@RabbitListener(
			bindings = {@QueueBinding(
					value = @Queue(value ="annQueue" ),
					exchange = @Exchange(value = "annExchange"),
					key = "annKey"
			)}
	)
	public void annListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("topic id msg" + msg.getMessageProperties());//消息中的一些参数
		System.out.println("使用注解的方式 "  + new String(msg.getBody(),"UTF-8"));
	}
	@RabbitListener(queues="queue_one")
	public void queueListener(Message msg) throws UnsupportedEncodingException {
        System.out.println("queue_one msg "  + new String(msg.getBody(),"UTF-8"));
    }

	@RabbitListener(queues="queue_two")
	public void objectListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("queue_two msg "  + new String(msg.getBody(),"UTF-8"));
	}

	@RabbitListener(queues="queue_three")
	public void objectListListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("queue_three msg "  + new String(msg.getBody(),"UTF-8"));
	}

	@RabbitListener(queues="topic_one")
	public void topicObjectListListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("topic id msg" + new String(msg.getBody(),"UTF-8"));
	}

	@RabbitListener(queues="topic_two")
	public void topic2ObjectListListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("topic2 id msg" + new String(msg.getBody(),"UTF-8"));
	}


	/*@RabbitListener(queues="fanout_one")
	public void fanoutOneListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("fanout_one " + new String(msg.getBody(),"UTF-8"));
	}

	@RabbitListener(queues="fanout_two")
	public void fanoutTwoListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("fanout_two " + new String(msg.getBody(),"UTF-8"));
	}*/


	@RabbitListener(queues="headers_one")
	public void headersOneListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("headers_one " + new String(msg.getBody(),"UTF-8"));
	}

	@RabbitListener(queues="headers_two")
	public void headersTwoListener(Message msg) throws UnsupportedEncodingException {
		System.out.println("headers_two " + new String(msg.getBody(),"UTF-8"));
	}

	@RabbitListener(queues="only")
	public void onlyListener(Message msg) throws UnsupportedEncodingException {
		String json = new String(msg.getBody(),"UTF-8");
		System.out.println("only " + json);

		MqTeachDTO mqTeachDTO = JSON.parseObject(json,MqTeachDTO.class);

		try {
			mqTeachMapper.insert(mqTeachDTO.getMqTeach());
			mqStudentMapper.insert(mqTeachDTO.getMqStudent());
		}catch (Exception e){
			System.out.println("error   "+mqTeachDTO.getMqTeach().getId());
		}
	}

	@RabbitListener(queues="all")
	public void allListener(Message msg) throws UnsupportedEncodingException {
		String json = new String(msg.getBody(),"UTF-8");
		System.out.println("all " + json);

		MqTeachDTO mqTeachDTO = JSON.parseObject(json,MqTeachDTO.class);

		try {
			mqTeachMapper.insert(mqTeachDTO.getMqTeach());
			mqStudentMapper.insert(mqTeachDTO.getMqStudent());
		}catch (Exception e){
			System.out.println("error   "+mqTeachDTO.getMqTeach().getId());
		}

	}
}
