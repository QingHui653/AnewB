package newb.c.a_spring.backend.rabbit;

import newb.c.a_spring.backend.sql.model.basemodel.Result;
import newb.c.util.common.ResultUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

public class SendRunable implements Runnable {

    private String exchange;

    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public SendRunable(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public SendRunable(String exchange, String routingKey, RabbitTemplate rabbitTemplate) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run() {
        ResultUtil obj = new ResultUtil();
        for (int i = 0; i < 50; i++) {
            obj.setSuccessful(true);
            obj.setResultmessage(exchange+"-"+routingKey+"-"+i+"：在"+System.currentTimeMillis()+"时间生成了ID为+"+UUID.randomUUID()+"的对象");
            rabbitTemplate.convertAndSend(exchange,routingKey,obj);
        }
    }
}
