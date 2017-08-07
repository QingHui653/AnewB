package newb.c.a_module.jobs.clusterquarz.job;

import java.util.Date;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ClusterQuartz {

	public void printUserInfo() {
		System.out.println("***      start " + DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS")+ "    *************");

		System.out.println("*");
		System.out.println("*        current username is " + System.getProperty("user.name"));
		System.out.println("*        current os name is " + System.getProperty("os.name"));
		System.out.println("*");

		System.out.println("*********current user information end******************");
	}
}
