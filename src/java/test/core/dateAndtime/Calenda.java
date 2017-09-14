package test.core.dateAndtime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calenda {

	/**
	 * 在实际使用中 错误的输入也能转化成功,因此使用jodaTime
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		Calendar c =Calendar.getInstance();
		Calendar c=GregorianCalendar.getInstance();
		Date d= new Date();
		d.getYear();
		SimpleDateFormat s= new SimpleDateFormat("yyyyMMdd");
		System.out.println("今天日期"+s.format(c.getTime()));
		c.add(Calendar.MONTH, -9);
		System.out.println("九月前"+s.format(c.getTime()));
		System.out.println("是否瑞年"+c.isLenient());

		String date1 ="2017-08-19";
		String date2 ="201708999";
		System.out.println("错误格式1  "+s.parse(date1));
		System.out.println("错误格式2  "+s.parse(date2));


	}
}
