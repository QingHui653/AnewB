package newb.c.a_spring.api.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
public class KafkaMqListener {

    @KafkaListener(topics = "annTest",containerGroup = "test-consumer-group")
    public void onTest(ConsumerRecord<String, Object> record){
        log.info("=============注解 监听=============");
        String topic = record.topic();
        String key = record.key();
        String value = record.value().toString();
        long offset = record.offset();
        int partition = record.partition();
        log.info("-------------topic:"+topic+"-key:"+key+"-value:"+value+"-ffset:"+offset+"-partition:"+partition);
    }
}
