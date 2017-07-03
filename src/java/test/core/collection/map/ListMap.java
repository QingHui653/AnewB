package test.core.collection.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMap {
	public static void main(String[] args) {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("name", "zhangsan");
		
		mapList.add(map);
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", 1);
		map1.put("name", "lisi");
		
		mapList.add(map1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", 2);
		map2.put("name", "wangwu");
		
		map2.put("name", "wangwu2");
		
		mapList.add(map2);
		
		for (Map<String, Object> m : mapList) {
			System.out.print(m.get("id")+"  ");
			System.out.println(m.get("name"));
		}
		
	}
}
