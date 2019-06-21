package test.core.DesignMode.Proxy_Dynamic;

import java.lang.reflect.InvocationHandler;  
import java.lang.reflect.Method;  
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {  
    
    private Object targetObject;  
      
    public Object newProxyInstance(Object targetObject) {  
        this.targetObject = targetObject;  
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(), this);  
    }  
      
    public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {  
        Object ret = null;  
              
        try {  
            System.out.println("正在进行操作前的准备工作……");  
            //调用目标方法  
            ret = method.invoke(targetObject, args);  
            System.out.println("操作成功，正在进行确认处理……");  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("error-->>" + method.getName());  
            throw e;  
        }  
        return ret;  
    }  
}  