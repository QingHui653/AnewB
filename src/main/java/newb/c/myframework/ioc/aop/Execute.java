package newb.c.myframework.ioc.aop;

import net.sf.cglib.proxy.MethodProxy;
import newb.c.myframework.ioc.aop.bean.AfterAdvice;
import newb.c.myframework.ioc.aop.bean.BeforeAdvice;
import newb.c.myframework.ioc.aop.bean.SurroundAdvice;

public class Execute {  
    public static Object executeAfter  
            (Object o, Object[] objects, MethodProxy methodProxy, AfterAdvice advice) throws Throwable {  
        Object object=methodProxy.invokeSuper(o,objects);  
        advice.after();  
        return object;  
    }  
    public static Object executeBefore  
            (Object o, Object[] objects, MethodProxy methodProxy, BeforeAdvice advice) throws Throwable {  
    	advice.before();  
        Object object=methodProxy.invokeSuper(o,objects);  
  
        return object;  
    }  
    public static Object executeSurround  
            (Object o, Object[] objects, MethodProxy methodProxy, SurroundAdvice advice) throws Throwable {  
        advice.before();  
        Object object=methodProxy.invokeSuper(o,objects);  
        advice.after();  
        return object;  
    }  
} 
