package test.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;

import newb.c.backend.model.basemodel.User;  
import test.core.javacase.a_99;

public class reflectT {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException{
		User user2 =new User();
		String classname="User";
		//获取Class类的三种方法
		new reflectT().rec(User.class);
//		new reflectT().rec(user2.getClass());
//		new reflectT().rec(Class.forName(classname));
		Method m =  user2.getClass().getDeclaredMethod("invMen", new Class[]{String.class,String.class});
		try {
			m.setAccessible(true);
			String result= (String) m.invoke(user2.getClass().newInstance(), "456","456");
			System.out.println(result);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void rec(Class<?> cl) throws IllegalArgumentException{
//		Class cl=User.class;
		
		try {
		System.out.println("getName 包名+全名"+cl.getName());
		System.out.println("getSimpleName 名+"+cl.getSimpleName());
		System.out.println("getMethods 方法名 "+Arrays.toString(cl.getMethods()));
		System.out.println("getDeclaredMethods 方法名2 "+Arrays.toString(cl.getDeclaredMethods()));//能获取到私有的方法
		System.out.println("getFields 字段名 "+Arrays.toString(cl.getFields()));//获取声明字段，字段全是私有的
		System.out.println("getDeclaredFields 字段名 "+Arrays.toString(cl.getDeclaredFields()));//获取字段，能获取到私有的，字段全是私有的

		Object ob = cl.newInstance();   //新建实例
		Field[] f= cl.getDeclaredFields();
		for (Field ff : f) {
			boolean isStatic = Modifier.isStatic(ff.getModifiers());
			if(isStatic)break;
			ff.setAccessible(true);
			String ffType= ff.getGenericType().getTypeName();
			//todo 判断类型,手动进行特殊的转换
			System.out.println("是否静态变量 "+isStatic);
			System.out.println("字段ff的类型为"+ffType+" ");
			ff.set(ob, ff.getName());
		}
		
		Method[] methods= cl.getDeclaredMethods();
		for (Method method : methods) {
//			System.out.println("方法名为 "+method.getName());
			if(method.getName().equals("invMen")){
				try {
					method.setAccessible(true);
					System.out.println("方法名为 "+method.getName());
					String result= (String) method.invoke(ob, "123","123");
					System.out.println(result );
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public <T> Object ListMapToBean(List<HashMap<String, Object>> ListMap,Class<T> cl) throws InstantiationException, IllegalAccessException{
		List<T> ListBean=new ArrayList<T>();
		for (HashMap<String, Object> hashMap : ListMap) {
			T ob = cl.newInstance();
			HashMap<String, Object> m2= new HashMap<String, Object>();
			for (Entry<String, Object> entry:hashMap.entrySet()) {
				m2.put(entry.getKey().replace("_", ""), entry.getValue());
			}
			for (Field ff : cl.getDeclaredFields()) {
				ff.setAccessible(true);
				ff.set(ob, m2.get(ff.getName().toUpperCase()));
			}
			ListBean.add(ob);
		}
		return ListBean;
	}
	
}
