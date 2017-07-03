package test.core.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calenda {

	public static void main(String[] args) {
//		Calendar c =Calendar.getInstance();
		Calendar c=GregorianCalendar.getInstance();
		Date d= new Date();
		d.getYear();
		SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("今天日期"+s.format(c.getTime()));
		c.add(Calendar.MONTH, -9);
		System.out.println("九月前"+s.format(c.getTime()));
		System.out.println("是否瑞年"+c.isLenient());
		
		for (int i = 0; i < 100000; i++) {
			c.setTimeInMillis(System.currentTimeMillis());
			System.out.println("当前  i"+i+"---"+s.format(c.getTime()));
		}
	}
}
