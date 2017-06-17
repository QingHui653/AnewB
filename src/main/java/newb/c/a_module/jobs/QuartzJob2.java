package newb.c.a_module.jobs;

import newb.c.util.DateUtil;

/**
 * 与Spring-job2配合使用，Spring配置文件只能存在一种
 * Quartz 只能使用一个
 * Created by woshizbh on 2016/12/3.
 */
public class QuartzJob2 {
    public void doQuartzJob2() {
        System.out.println(DateUtil.getForDate()+"  不继承QuartzJobBean方式-调度进行中...");
    }
}
