package newb.c.a_spring.a_module.jobs.clusterquarz.cron;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {

	@Override
	public void afterPropertiesSet() throws ParseException {
		super.afterPropertiesSet();
		getJobDataMap().remove(getObject().getJobKey().getName());
	}
}
