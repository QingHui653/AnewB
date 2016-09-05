package newb.c.util;

import java.util.Map;
import java.util.HashMap;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		
		for (Map.Entry<String,String> entry:map.entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}

}
