package test.core.dateAndtime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class JodaTest {

    /**
     * 使用joda 错误的格式 无法解析
     */
    @Test
    public void jodaUtil(){


        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");

        String date ="20170819";
        String date1 ="2017-08-19";
        String date2 ="201708999";

        DateTime dateStr =dtf.parseDateTime(date);

        System.out.println(dateStr.toDate());

        System.out.println("正确格式  "+dtf.parseDateTime(date));
        System.out.println("错误格式1  "+dtf.parseDateTime(date1));
        System.out.println("错误格式2  "+dtf.parseDateTime(date2));
    }
    
    @Test
    public void jodaWeek() {
    	DateTime dateTime = new DateTime();
    	System.out.println(dateTime.toString());
    	dateTime.plusDays(1).plusWeeks(1);
    	System.out.println(dateTime.toString());
    }
}
