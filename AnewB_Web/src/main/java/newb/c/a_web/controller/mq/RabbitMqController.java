package newb.c.a_web.controller.mq;

import lombok.extern.slf4j.Slf4j;
import newb.c.a_spring.backend.sql.model.basemodel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * JMS|AMQP
 * Java api(不跨java)|Wire-protocol(跨java)
 * p2p,pub/sub| direct fanout topic headers  system 五种类型 后四种和JMS的pub/sub模型没有太大差别，仅是在路由机制上做了更详细的划分；
 */

/**
 * java 端口 为 5672 web 端口为 15672
 */
@Controller
@RequestMapping("rabbitmq")
@Slf4j
public class  RabbitMqController {
    @Autowired(required=false)
    private RabbitTemplate rabbitTemplate;
//    @Autowired(required=false)
//    private AmqpTemplate rabbitTemplate;


    @GetMapping("queue ann")
    public void testAnnRabbit() {
        rabbitTemplate.convertAndSend("annExchange","annKey", "注解配置");
    }

    @GetMapping("queue string")
    public void testRabbit() {
        rabbitTemplate.convertAndSend("queue_one", "hhhhhh");
    }

    @GetMapping("queue object")
    public void testObjectRabbit() {
        User u =new User(1,"二二","queue_two");
        rabbitTemplate.convertAndSend("queue_two", u);
    }

    @GetMapping("queue objectList")
    public void testObjectListRabbit() {
        List l =new ArrayList();
        User u =new User(1,"33","queue_three");
        User u2 =new User(2,"33","queue_three");
        l.add(u);
        l.add(u2);
        rabbitTemplate.convertAndSend("queue_three", l);
    }

    @GetMapping("topic one")
    public void testTopicRabbit() {
        rabbitTemplate.convertAndSend("topicExchange","quick.orange.rabbit","quick.orange.rabbit 转发两个");
        rabbitTemplate.convertAndSend("topicExchange","lazy.orange.elephant","lazy.orange.elephant 转发两个");
        rabbitTemplate.convertAndSend("topicExchange","quick.orange.fox","quick.orange.fox 转发topic one");
        rabbitTemplate.convertAndSend("topicExchange","lazy.brown.fox","lazy.brown.fox 转发topic two");
    }

    @GetMapping("topic objectList")
    public void testTopicObjectListRabbit() {
        List l =new ArrayList();
        User u =new User(1,"二二","topic_three");
        User u2 =new User(2,"二二","topic_three");
        l.add(u);
        l.add(u2);
        rabbitTemplate.convertAndSend("topicExchange","topic.one.two", l);
    }

    @GetMapping("fanout")
    public void testFanoutRabbit() {
        rabbitTemplate.convertAndSend("fanoutExchange",null,"fanoutExchange 转发 全部");
    }

    /**
     * Hash结构中要求携带一个键“x-match”，这个键的Value可以是any或者all，这代表消息携带的Hash是需要全部匹配(all)，还是仅匹配一个键(any)就可以了
     * @throws UnsupportedEncodingException
     */
    @GetMapping("headers")
    public void testHeaderRabbit() throws UnsupportedEncodingException {
        MessageProperties properties1 = new MessageProperties();
        properties1.setHeader("head_one_one","head_one_one");
//        properties1.setHeader("head_one_two","head_one_one");
//        properties1.setHeader("head_one_two","head_one_two");
//        properties1.setHeader("headOne","headOne");
//        properties1.setHeader("x-match","any");
        String body1 ="headone 转发"; //需要使用UTF-8编码 发送与监听编码需要一致
        Message message1 = new Message(body1.getBytes("UTF-8"),properties1);
        rabbitTemplate.convertAndSend("headersExchange",null,message1);

        MessageProperties properties2 = new MessageProperties();
        properties2.setHeader("head_two_one","head_two_one");
        properties2.setHeader("head_two_two","head_two_two");
//        properties2.setHeader("head_two_two","head_two_one");
//        properties2.setHeader("headTwo","headTwo");
//        properties2.setHeader("x-match","any");
        String body2 ="headtwo 转发";
        Message message2 = new Message(body2.getBytes("UTF-8"),properties2);
        rabbitTemplate.convertAndSend("headersExchange",null,message2);
    }
}
