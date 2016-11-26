package newb.c.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import newb.c.model.UserXL;

public class JsonUtil {
	
	
	
	public JsonUtil() {
		super();
	}

	public <T> Object jsonToObj(String json,Class<T> cl) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
		Object ob = cl.newInstance();
		Map<String, String> m =jsonToMap(json);
//		System.out.println("---"+m.get("hjkk"));
		/*for (Map.Entry<String, String> entry:m.entrySet()) {
			System.out.println("1111"+entry.getKey());
			Field ff= cl.getDeclaredField(entry.getKey()) ;
			ff.setAccessible(true);
			ff.set(ob, entry.getValue());
		}*/
		for (Field ff : cl.getDeclaredFields()) {
			ff.setAccessible(true);
//			System.out.println("ff"+ff.getName());
//			System.out.println("ff"+ff.getType().toString());
//			System.out.println("ff"+m.get(ff.getName()));
			
			if(ff.getGenericType().getTypeName() instanceof  String)
			if (ff.getType().toString().equals("class java.lang.Integer")) {
				ff.set(ob, Integer.valueOf(m.get(ff.getName())));
			}else {
				ff.set(ob, m.get(ff.getName()));
			}
		}
		return ob;
	}
	
	public Map<String, String> jsonToMap(String json) {
//		System.out.println("----+"+json.trim());
		String jsonString=json.trim().substring(1,json.length()-1);
		String[] mapList=jsonString.split(",");
		Map<String, String> map= new HashMap<String, String>();
		for (String string : mapList) {
			String[] mapData= string.trim().split("=");
			map.put(mapData[0],mapData[1]);
		}
		return map;
	}
}
