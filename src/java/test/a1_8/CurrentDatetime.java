package test.a1_8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;



public class CurrentDatetime {
	public static void main(String[] args) {
		LocalDate dNow = LocalDate.now();
		System.out.println(dNow);
		LocalTime tNow = LocalTime.now();
		System.out.println(tNow);
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(df.format(LocalDateTime.now()));
		System.out.println(LocalDate.parse("2016/11/28", df));
		DateTimeFormatter nTZ = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
		System.out.println(ZonedDateTime.now().format(nTZ));
		
		DateTimeFormatter dfc = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(LocalDateTime.now().format(dfc));
		/*
		 * 输出： 2017-04-02 16:43:29.988 2017-04-02T16:43:29.988 2017/04/02
		 * 2016-11-28 2 四月, 2017 4:43 下午
		 */
	}
}
