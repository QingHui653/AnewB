import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SourceCodeTest {

    @Test
    public void  collectionTest(){
        List arrayList = new ArrayList(10);
        List linkedList = new LinkedList();

        Map map =new HashMap();
        Map treeMap = new TreeMap();
        Map linkedHashMap =new LinkedHashMap();
        Map table =new Hashtable();

        Set set = new HashSet();
        Set treeSet = new TreeSet();
        Set linkHashSet = new LinkedHashSet();

    }

    @Test
    public void currentCollectionTest(){
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
