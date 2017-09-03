package newb.c.api.mq.rabbitmq;

import newb.c.backend.model.basemodel.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListener {
	
	@RabbitListener(queues="queue_one")
	public void queueListener(Object id, Object jobName){
		System.out.println("Received request for id " + id.toString());
        System.out.println("Received request for job name " + jobName);
    }

	@RabbitListener(queues="queue_two")
	public void objectListener(User u, Object jobName){
		System.out.println("Received request for id " + u.toString());
		System.out.println("Received request for Total " + jobName);
	}

	@RabbitListener(queues="queue_three")
	public void objectListListener(Object l, Object jobName){
		System.out.println("Received request for id " + l);
		System.out.println("Received request for Total " + jobName);
	}
}
