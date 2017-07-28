package test.core.java1_8;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import org.junit.Test;
/**
 * java1.8 时间日期API的使用
 * 瞬时时间（Instant）,持续时间（duration），日期（date）,时间（time），时区（time-zone）以及时间段（Period）
 * Instant——它代表的是时间戳
 * LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等
 * LocalTime——它代表的是不含日期的时间
 * LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
 * ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的
 * @author woshizbh
 *
 */
public class CurrentDatetime {
	
	/**
	 * 
	 */
	@Test
	public void timeUnit() {
		//1.获取当天的日期
		LocalDate today = LocalDate.now(); System.out.println("今天的日期为 : " + today); 
		//2.分别获取年月日
		int year =today.getYear();
		int month=today.getMonthValue();
		int day =today.getDayOfMonth();
		System.out.printf("年 : %d 月 : %d 日 : %d \t %n", year, month, day);
		//3.在java中获取特定的日期
		LocalDate dateOfBirth = LocalDate.of(2016, 07, 28); 
		System.out.println("获取到的dateOfBirth 为 : " + dateOfBirth); 
		//4.判断是否相等
		if(!dateOfBirth.equals(today)){
			System.out.println("不是同一天");
		}
		//5.检查重复事件
		MonthDay birthday =  MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(today); 
		if(currentMonthDay.equals(birthday)){ 
		    System.out.println("虽然年不同但还是会提示 !"); 
		}else{ 
		    System.out.println("日期不同"); 
		}
		//6.当前时间
		LocalTime time = LocalTime.now(); System.out.println("当前时间 : " + time);
		//7.增加小时
		LocalTime newTime = time.plusHours(2); // adding two hours 
		System.out.println("两个小时后 : " + newTime); 
		//8.一周后
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		System.out.println("一周后 : " + nextWeek);
		//9.一年前后
		LocalDate previousYear = today.minus(1, ChronoUnit.YEARS); 
		System.out.println("一年前 : " + previousYear); 
		LocalDate nextYear = today.plus(1, ChronoUnit.YEARS); 
		System.out.println("一年后 : " + nextYear); 
		//10使用时钟
		// Returns the current time based on your system clock and set to UTC. 
		Clock clock = Clock.systemUTC(); 
		System.out.println("Clock : " + clock); 
		// Returns time based on system clock zone Clock defaultClock = 
		Clock.systemDefaultZone(); 
		System.out.println("Clock : " + clock); 
		//11.判断日期
		LocalDate tomorrow = LocalDate.of(2014, 1, 15); 
		if(tomorrow.isAfter(today)){ 
		    System.out.println("Tomorrow comes after today"); 
		} 
		LocalDate yesterday = today.minus(1, ChronoUnit.DAYS); 
		if(yesterday.isBefore(today)){ 
		    System.out.println("Yesterday is day before today"); 
		} 
		//12.处理时区
		ZoneId america = ZoneId.of("America/New_York"); 
		LocalDateTime localtDateAndTime = LocalDateTime.now(); 
		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime,america  ); 
		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
		//13.表示固定的日期
		YearMonth currentYearMonth = YearMonth.now(); System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth()); 
		YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY); 
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
		//14.Java 8中检查闰年
		if(today.isLeapYear()){ 
		    System.out.println("This year is Leap year"); 
		}else { 
		    System.out.println("2014 is not a Leap year"); 
		}
		//15.两个日期之间包含多少天，多少个月
		LocalDate java8Release = LocalDate.of(2014, Month.MARCH, 14); 
		Period periodToNextJavaRelease = 
		Period.between(today, java8Release); 
		System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths() );
		//16.带时区偏移量的日期与时间
		LocalDateTime datetime = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30); 
		ZoneOffset offset = ZoneOffset.of("+05:30"); 
		OffsetDateTime date = OffsetDateTime.of(datetime, offset); 
		System.out.println("Date and Time with timezone offset in Java : " + date); 
		
		//17.在Java 8中如何获取当前时间戳
		Instant timestamp = Instant.now(); 
		System.out.println("What is value of this instant " + timestamp); 
		//18.如何在Java 8中使用预定义的格式器来对日期进行解析/格式化
		String dayAfterTommorrow = "20140116"; 
		LocalDate formatted = LocalDate.parse(dayAfterTommorrow, 
		DateTimeFormatter.BASIC_ISO_DATE); 
		System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted); 
		//19.在Java中使用自定义的格式器来解析日期
		String goodFriday = "Apr 18 2014"; 
		try { 
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");     
		    LocalDate holiday = LocalDate.parse(goodFriday, formatter); 
		    System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday); 
		} catch (DateTimeParseException ex) { 
		    System.out.printf("%s is not parsable!%n", goodFriday); 
		    ex.printStackTrace(); 
		} 
		//20.在Java 8中对日期进行格式化，转换成字符串
		LocalDateTime arrivalDate = LocalDateTime.now(); 
		try { 
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"); 
		    String landing = arrivalDate.format(format); 
		    System.out.printf("Arriving at : %s %n", landing); 
		    } catch (DateTimeException ex) { 
		    System.out.printf("%s can't be formatted!%n", arrivalDate); 
		    ex.printStackTrace(); 
		} 
	}
	
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
