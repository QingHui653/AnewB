package newb.c.a_module.jobs.clusterquarz.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.util.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-23
 */
public class PrintCurrentTimeJobs extends QuartzJobBean {
	private static final Log LOG_RECORD = LogFactory.getLog(PrintCurrentTimeJobs.class);

	@Autowired
    private ClusterQuartz clusterQuartz;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LOG_RECORD.info("begin to execute task," + DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));

		clusterQuartz.printUserInfo();

		LOG_RECORD.info("end to execute task," + DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));

	}
}
