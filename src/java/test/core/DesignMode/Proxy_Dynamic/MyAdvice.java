package test.core.DesignMode.Proxy_Dynamic;

import java.lang.reflect.Method;

public class MyAdvice implements Advice {

	long beginTime = 0 ;
    @Override
    public void beforeMethod(Method method) {
        System.out.println(method.getName()+" before at "+beginTime);
        beginTime = System.currentTimeMillis();
    }

    @Override
    public void aftereMethod(Method method) {
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName()+" cost total "+ (endTime-beginTime));
    }

}
