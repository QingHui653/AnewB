package test.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

import newb.c.model.User;
import test.aa.a_99;

public class reflectT {
	public static void main(String[] args){
		new reflectT().rec(User.class);
	}
	
	@Test
	public void rec(Class cl) {
//		Class cl=User.class;
		
		try {
		System.out.println("getName 包名+全名"+cl.getName());
		System.out.println("getSimpleName 名+"+cl.getSimpleName());
		
		System.out.println("getMethods 方法名 "+Arrays.toString(cl.getMethods()));
		Object ob = cl.newInstance();   //新建实例
		System.out.println("getDeclaredFields 字段名 "+Arrays.toString(cl.getDeclaredFields()));
		Field[] f= cl.getDeclaredFields();
		for (Field ff : f) {
			ff.setAccessible(true);
			ff.set(ob, ff.getName());
		}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
