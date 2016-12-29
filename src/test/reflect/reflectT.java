package test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;

import newb.c.backend.model.basemodel.User;  import test.aa.a_99;

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
			String ffType= ff.getGenericType().getTypeName();
			//todo 判断类型,手动进行特殊的转换
			System.out.println("字段ff的类型为"+ffType+" ");
			ff.set(ob, ff.getName());
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
