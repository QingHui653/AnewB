import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

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

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

    }
}
