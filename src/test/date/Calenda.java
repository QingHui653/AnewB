package test.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calenda {

	public static void main(String[] args) {
//		Calendar c =Calendar.getInstance();
		Calendar c=GregorianCalendar.getInstance();
		SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("今天日期"+s.format(c.getTime()));
		c.add(Calendar.MONTH, -9);
		System.out.println("九月前"+s.format(c.getTime()));
		System.out.println("是否瑞年"+c.isLenient());
	}

}
