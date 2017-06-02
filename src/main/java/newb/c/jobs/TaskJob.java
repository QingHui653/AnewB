package newb.c.jobs;

/**
 * Created by woshizbh on 2016/12/3.
 */

import newb.c.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {
    @Scheduled(cron = "0 0/10 * * * ?")
    public void job1() {
        System.out.println(DateUtil.getForDate()+" Task任务进行中。。。");
    }
}

