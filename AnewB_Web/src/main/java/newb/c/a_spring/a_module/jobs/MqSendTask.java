package newb.c.a_spring.a_module.jobs;

import newb.c.a_spring.backend.rabbit.RabbitService;
import newb.c.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("mqSendTask")
public class MqSendTask {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired(required=false)
    private RabbitService rabbitService;

    @Scheduled(cron = "0/60 * * * * ? ")
    private void priceJob() {
        try {
            log.info(DateUtil.getForDate()+" send mq");
            if(rabbitService!=null){
                rabbitService.concurrentSend();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
