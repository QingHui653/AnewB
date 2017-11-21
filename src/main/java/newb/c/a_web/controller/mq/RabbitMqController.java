package newb.c.a_web.controller.mq;

import newb.c.a_spring.backend.sql.model.basemodel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class  RabbitMqController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

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
        rabbitTemplate.convertAndSend("topicExchange","topic.one","hhhtopic");
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
}
