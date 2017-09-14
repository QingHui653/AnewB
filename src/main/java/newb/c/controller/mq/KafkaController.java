package newb.c.controller.mq;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import newb.c.api.mq.kafka.KafkaProducerServer;


@Controller
@RequestMapping("kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    
    @Autowired(required=false)
    private KafkaProducerServer kafkaProducer;
    
    @Autowired(required=false)
    private KafkaTemplate kafkaTemplate;

    @GetMapping("string")
    public void testKafka() {
    	 String topic = "test";
         String value = "test  String";
         String ifPartition = "0";
         Integer partitionNum = 2;
         String role = "test";//用来生成key
         Map<String,Object> res = kafkaProducer.sndMesForTemplate
                 (topic, value, ifPartition, partitionNum, role);
         
         System.out.println("测试结果如下：===============");
         String message = (String)res.get("message");
         String code = (String)res.get("code");
         
         System.out.println("code:"+code);
         System.out.println("message:"+message);
	}
    
    @RequestMapping(value = "/test")
    @ResponseBody
    public  Object index() {
        for (int i = 0; i < 50; i++) {
            logger.info("======send=====" + i);
            kafkaTemplate.send("test", "key", "测试"+i);
        }
        return "finished";
    }
}
