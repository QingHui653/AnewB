package newb.c.myframework.mvc.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import newb.c.myframework.mvc.LoginServlet2;

public class AnnotationResolver {

	/**
	 * 简单的注解处理器
	 * @param authorClass
	 */
	public static void parseAnnotation(Class<?> class1){
		//获取类上面标注的指定类型的注解
		//类
		MyController[] myControllers = class1.getAnnotationsByType(MyController.class);
		
		Field[] fields = class1.getFields();
		
		Method[] methods = class1.getMethods();
		//遍历取值
		
		//类
		if(myControllers != null && myControllers.length > 0){
			for(MyController tmp : myControllers){
				System.out.println("controller：" + tmp.value());
			}
		}
		
		//方法
		for (Method method : methods) {
			if(method.isAnnotationPresent(MyRequestMapping.class)){
				MyRequestMapping MyRequestMapping = method.getAnnotation(MyRequestMapping.class);
				System.out.println("requestMapping： " + MyRequestMapping.value());
			}
		}
		
		//字段
		for(Field field : fields){
            if(field.isAnnotationPresent(MyRequestMapping.class)){
            	MyRequestMapping MyRequestMapping = field.getAnnotation(MyRequestMapping.class);
            	System.out.println("值名称： " + MyRequestMapping.value());
            }
        }
	}
	
	public static void main(String[] args) {
		AnnotationResolver.parseAnnotation(LoginServlet2.class);
	}
}
