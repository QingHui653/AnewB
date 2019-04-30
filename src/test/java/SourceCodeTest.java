import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class SourceCodeTest {

    @Test
    public void  collectionTest(){
        List arrayList = new ArrayList(10);
        List linkedList = new LinkedList();

        HashMap map =new HashMap();
        TreeMap treeMap = new TreeMap();
        LinkedHashMap linkedHashMap =new LinkedHashMap();
        Hashtable table =new Hashtable();

        HashSet set = new HashSet();
        set.add("1");
        TreeSet treeSet = new TreeSet();
        LinkedHashSet linkHashSet = new LinkedHashSet();

    }

    @Test
    public void currentCollectionTest(){

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();

    }

    public void lock(){
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
    }

    public void poolTest(){
        Executor executors = Executors.newCachedThreadPool();
    }
}
