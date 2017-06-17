package newb.c.a_module.jobs;

import newb.c.util.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 与SpringT-job配合使用，只能有一个spring配置文件
 * Quartz 只能使用一个
 * Created by woshizbh on 2016/12/3.
 */

public class QuartzJob1 extends QuartzJobBean {

    private int timeout;
    private static int i = 0;
    //调度工厂实例化后，经过timeout时间开始执行调度
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 要调度的具体任务
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(DateUtil.getForDate()+" QuartJob1 定时任务执行中…");
    }
}
