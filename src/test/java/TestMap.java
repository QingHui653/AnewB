import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {

    public static void main(String[] args) {

        HashMap map = new HashMap(1);
        map.put("hashMap", "hashMap");
        map.put("hashMap2", "hashMap");
        map.put(null, null);
        map.put(null, "null");
        String hMap = (String) map.get("hashMap");

        ConcurrentHashMap conMap = new ConcurrentHashMap();

        conMap.put("hashMap", "hashMap");
        conMap.put("hashMap2", "hashMap2");
//        conMap.put(null, null);
//        conMap.put(null, "null");
        String cMap = (String) conMap.get("hashMap");

        System.out.println("hmap " + hMap + " cmap " + cMap);
    }


}
