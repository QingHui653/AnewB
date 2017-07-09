package test.core.DesignMode.Proxy_Dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
/**
 * 动态代理模式
 * @author woshizbh
 *
 */
public class AdviceDemo {
	public static void main(String[] args) {
		Collection<Object> proxy3=(Collection) getproxy(new ArrayList<>(), new MyAdvice());
		proxy3.add("111");
		proxy3.add("222");
		System.out.println(proxy3.size());
		
	}
	
	public static Object getproxy(final Object Target,final Advice advice ) {
		Object proxy=Proxy.newProxyInstance(
				Target.getClass().getClassLoader(), 
				Target.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						advice.beforeMethod(method);
						Object retVal=method.invoke(Target, args);
						advice.aftereMethod(method);
						return retVal;
					}
				});
		return proxy;
	}
}
