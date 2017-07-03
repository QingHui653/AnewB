package test.core.collection.map;

import java.util.Map;
import java.util.HashMap;



public class HashMapTest {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		
		System.out.println(map.containsKey("1"));
		//使用entry 迭代 Map
		for (Map.Entry<String,String> entry:map.entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	
	}


}
