package newb.c.a_module.jobs.clusterquarz.job;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-23
 */
public class Test {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:main/resources/job/spring-quarz-distributed.xml");

		ClusterQuartz clusterQuartz = (ClusterQuartz) context.getBean("clusterQuartz");
		clusterQuartz.printUserInfo();

		while (true) {

		}
	}
}
