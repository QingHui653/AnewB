package test.core.thread.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 使用map为每个线程准备数据
 * @author woshizbh
 *
 */
public class ThreadScopeShareData {
    //准备一个哈希表，为每个线程准备数据
    private  static volatile Map<Thread,Integer> threadData = new HashMap<>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new Thread(
                    new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    threadData.put(Thread.currentThread(),data);
                    System.out.println(Thread.currentThread()+" put data："+data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }
   static  class A{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from "+Thread.currentThread()+" get data "+data);
        }
    }

    static  class B{
        public void get(){
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from "+Thread.currentThread()+" get data "+data);
        }
    }
}
