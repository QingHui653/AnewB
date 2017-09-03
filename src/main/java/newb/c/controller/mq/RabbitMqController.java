package newb.c.controller.mq;

import newb.c.backend.model.basemodel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("rabbitmq")
public class RabbitMqController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    //ActiveMQ
    @Autowired(required=false)
    private RabbitTemplate rabbitTemplate;

    @GetMapping("string")
    public void testRabbit() {
        rabbitTemplate.convertAndSend("queue_one", "hhhhhh");
    }

    @GetMapping("object")
    public void testObjectRabbit() {
        User u =new User(1,"二二","三三");
        rabbitTemplate.convertAndSend("queue_two", u);
    }

    @GetMapping("objectList")
    public void testObjectListRabbit() {
        List l =new ArrayList();
        User u =new User(1,"二二","三三");
        User u2 =new User(2,"二二","三三");
        l.add(u);
        l.add(u2);
        rabbitTemplate.convertAndSend("queue_three", l);
    }
}
