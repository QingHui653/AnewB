package newb.c.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by woshizbh on 2016/12/3.
 */
public class DateUtil {

    public static String DATE ="yyyy-MM-dd HH:mm:ss";

    /**
     * 获取yyyy-MM-dd HH:mm:ss格式时间字符串
     * @return
     */
    public static String getForDate(){
        SimpleDateFormat sdf=new SimpleDateFormat(DATE);
        String d= sdf.format(new Date());
        return d;
    }
}
