package newb.c.a_spring.a_module.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinghui
 * @Description: TODO
 * @date 2019/7/13 10:36
 */

public class RocketMqCumsterTest {

    private String namesrvAddr ="192.168.8.12:19876";

    private String group ="DELIVERY-ORDER-INFO-FLOW-CONSUMER-GROUP";

    private String topic  ="DELIVERY-ORDER-INFO-TOPIC";

    private int consumeMessageBatchMaxSize=5;

    private int pullThresholdForQueue =5;

    private DefaultMQPushConsumer consumer = null;

    private Logger log = LoggerFactory.getLogger(RocketMqCumsterTest.class);

    @Test
    public void run() {
        try {
            consumer = new DefaultMQPushConsumer(group);
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(topic, (String) null);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
            consumer.setInstanceName("1");
            consumer.setPullThresholdForQueue(pullThresholdForQueue);
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                try {
                    for (Message msg : msgs) {
                        System.out.println(msg);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    log.error("OrderInfoConsumer process message error", e);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            consumer.start();
            log.info("OrderInfoConsumer init, nameSrv: {}", namesrvAddr);
        } catch (Exception e) {
            log.error("OrderInfoConsumer init error, nameSrv: {}", namesrvAddr, e);
        }
    }
}