package newb.c.a_spring.backend.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class RabbitServiceImpl implements RabbitService {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired(required=false)
    private RabbitTemplate rabbitTemplate;

    @Override
    public void concurrentSend() {
        executorService.execute(new SendRunable("fanoutExchange","all",rabbitTemplate));
        executorService.execute(new SendRunable("directExchange","only",rabbitTemplate));
    }
}
