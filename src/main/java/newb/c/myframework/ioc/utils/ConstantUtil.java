package newb.c.myframework.ioc.utils;

import java.util.Map;

/**
 * Created by 10033 on 2017/5/12.
 */
public class ConstantUtil {
    private static final String PROPERTY_PATH="/scan-package.properties";
    public static final Map<String,String> PROPERTY_MAP=PropertiesReaderUtil.getProperties(PROPERTY_PATH);
}
