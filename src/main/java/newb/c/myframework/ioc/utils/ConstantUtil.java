package newb.c.myframework.ioc.utils;

import java.util.Map;

public class ConstantUtil {
    private static final String PROPERTY_PATH= "/resources/scan-package.properties";
    public static final Map<String,String> PROPERTY_MAP=PropertiesReaderUtil.getProperties(PROPERTY_PATH);
}
