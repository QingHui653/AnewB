package newb.c.a_spring.backend.rabbit;

import org.springframework.stereotype.Service;

@Service
public interface RabbitService {

    void concurrentSend();
}
