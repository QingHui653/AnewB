package newb.c.a_module.jobs;

/**
 * Created by woshizbh on 2016/12/3.
 */

import newb.c.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("taskJob")
public class TaskJob {
	//1000为1s  cron = "0 0 0/10 * * ?"
    @Scheduled(fixedDelay=60*60*1000)
    public void job1() {
        System.out.println(DateUtil.getForDate()+" Task任务进行中。。。");
    }
}

