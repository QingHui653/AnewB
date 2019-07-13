package newb.c.a_spring.a_module.mq.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinghui
 * @Description: TODO
 * @date 2019/7/13 10:36
 */

public class RocketMqProducerTest {

    private String namesrvAddr ="192.168.8.12:19876";

    private String group ="DELIVERY-ORDER-INFO-FLOW-CONSUMER-GROUP";

    private String topic  ="DELIVERY-ORDER-INFO-TOPIC";

    private Logger log = LoggerFactory.getLogger(RocketMqProducerTest.class);

    @Test
    public void init() {

        DefaultMQProducer deliveryOrderProductSkuProducer = new DefaultMQProducer(group);
        deliveryOrderProductSkuProducer.setNamesrvAddr(namesrvAddr);
        deliveryOrderProductSkuProducer.setVipChannelEnabled(false);
        deliveryOrderProductSkuProducer.setInstanceName("测试1");

        try {
            deliveryOrderProductSkuProducer.start();
            Map<String,String> map = new HashMap<>();
            map.put("key","3");
            map.put("msg","3");

            String messageBody = JSON.toJSONString(map);
            Message message = new Message(topic, null, "3", messageBody.getBytes());
            SendResult sendResult = deliveryOrderProductSkuProducer.send(message);
            System.out.println(sendResult.toString());
        } catch (MQClientException e) {
            log.error("deliveryOrderProductSkuProducer start error", e);
        } catch (Exception e) {
            log.error("deliveryOrderStockFlowTopic error, topic: {}, message: {}");
        }

        log.info("deliveryOrderProductSkuProducer init, name server : {}", namesrvAddr);
    }
}