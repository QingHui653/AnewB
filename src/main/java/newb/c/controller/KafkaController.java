package newb.c.controller;

import newb.c.api.kafka.KafkaProducerServer;
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
import java.util.Map;


@Controller
@RequestMapping("kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    
    @Autowired
    private KafkaProducerServer kafkaProducer;

    @GetMapping("string")
    public void testKafka() {
    	 String topic = "orderTopic";
         String value = "test";
         String ifPartition = "0";
         Integer partitionNum = 3;
         String role = "test";//用来生成key
         Map<String,Object> res = kafkaProducer.sndMesForTemplate
                 (topic, value, ifPartition, partitionNum, role);
         
         System.out.println("测试结果如下：===============");
         String message = (String)res.get("message");
         String code = (String)res.get("code");
         
         System.out.println("code:"+code);
         System.out.println("message:"+message);
	}
}
