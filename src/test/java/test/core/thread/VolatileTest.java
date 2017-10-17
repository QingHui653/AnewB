package test.core.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 并发三特性：原子性，可见性，有序性
 * @author woshizbh
 *
 */
public class VolatileTest {
    
//	public static ConcurrentMap<Object, Object> concurrentHashMap=new ConcurrentHashMap<Object, Object>();
	
//	public static Map<Object, Object> concurrentHashMap=new HashMap<Object, Object>();
	
	/*public void map(int j) {
		concurrentHashMap.put(j, j);
    }*/
	
	
	
    //第一
	/*public volatile int inc = 0; //保证可见性，但无法保证原子性
    public void increase() {
        inc++;
    }*/
    //第二
    //synchronized 保证原子性但不能保证有序性
    /*public volatile int inc = 0;
    public synchronized void increase() {
        inc++;
    }*/
    
    //第三 lock 加锁可以保证原子性可见证有序性
    /*public volatile int inc = 0;
    Lock lock = new ReentrantLock();
    public  void increase() {
        lock.lock();
        try {
            inc++;
        } finally{
            lock.unlock();
        }
    }*/
    //第四
    //在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，
    //即对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），减法操作（减一个数）进行了封装，保证这些操作是原子性操作。
    //atomic是利用CAS来实现原子性操作的（Compare And Swap），CAS实际上是利用处理器提供的CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子性操作。
    /*public  AtomicInteger inc = new AtomicInteger();
    public  void increase() {
        inc.getAndIncrement();
    }*/
//	public static ConcurrentLinkedQueue<Object> concurrentList= new ConcurrentLinkedQueue<>();
    
	public static List<Object> concurrentList=new ArrayList<Object>();
	
	public void list(int j) {
		concurrentList.add(j);
    }
	
    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
//                      test.core.increase();
//                    	test.core.map(j);
                    	test.list(j);
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
//        System.out.println(test.core.inc);
//        System.out.println(concurrentHashMap.toString());
        System.out.println(concurrentList.toString());
    }
}
