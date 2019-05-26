import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {

    public static void main(String[] args) {

        HashMap map = new HashMap(1);
//        map.put("hashMap", "hashMap");
//        map.put("hashMap2", "hashMap");
        map.put(null, null);
        map.put(null, "null");
        String hMap = (String) map.get("hashMap");

        ConcurrentHashMap conMap = new ConcurrentHashMap();
        List list = new ArrayList();
        List list2 = new LinkedList();
        conMap.put("hashMap2", "hashMap2");
//        conMap.put(null, null);
//        conMap.put(null, "null");
//        conMap.put("hashMap", "hashMap");
        String cMap = (String) conMap.get("hashMap");

        System.out.println("hmap " + hMap + " cmap " + cMap);
    }


}
