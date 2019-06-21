package newb.c.a_spring.a_config;

import lombok.extern.slf4j.Slf4j;
import newb.c.a_spring.api.mq.kafka.KafkaMqListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Service;

//@Service
//@EnableKafka()
@Slf4j
public class kafkaConfig {

    @Autowired
    private ConsumerFactory consumerFactory;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        log.info("初始化 KafkaListenerContainerFactory");
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public KafkaMqListener kafkaMqListener(){
        return new KafkaMqListener();
    }
}
