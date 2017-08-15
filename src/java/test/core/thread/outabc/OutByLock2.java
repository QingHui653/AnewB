package test.core.thread.outabc;

import java.util.concurrent.locks.Condition;  
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;  
  
import javax.xml.stream.events.StartDocument;  
  
public class OutByLock2 {  
    private static Lock lock = new ReentrantLock();  
    private static int count = 0;  
    private static Condition A = lock.newCondition();  
    private static Condition B = lock.newCondition();  
    private static Condition C = lock.newCondition();  
  
    static class ThreadA extends Thread {  
  
        @Override  
        public void run() {  
            lock.lock();  
            try {  
                for (int i = 0; i < 10; i++) {  
                    while (count % 3 != 0)  
                        A.await(); //如果不满足while条件，将本线程挂起  
                    System.out.print("A");  
                    count++;  
                    B.signal(); // A线程执行后，唤醒下一个线程B  
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } finally {  
                lock.unlock();  
            }  
        }  
  
    }  
  
    static class ThreadB extends Thread {  
  
        @Override  
        public void run() {  
            lock.lock();  
            try {  
                for (int i = 0; i < 10; i++) {  
                    while (count % 3 != 1)  
                        B.await();//如果不满足while条件， 将本线程挂起  
                    System.out.print("B");  
                    count++;  
                    C.signal();// B线程执行后，唤醒下一个线程C  
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } finally {  
                lock.unlock();  
            }  
        }  
  
    }  
  
    static class ThreadC extends Thread {  
  
        @Override  
        public void run() {  
            lock.lock();  
            try {  
                for (int i = 0; i < 10; i++) {  
                    while (count % 3 != 2)  
                        C.await();//如果不满足while条件， 将本线程挂起  
                    System.out.println("C");  
                    count++;  
                    A.signal();// C线程执行后，唤醒下一个线程A  
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } finally {  
                lock.unlock();  
            }  
        }  
  
    }  
  
    public static void main(String[] args) throws InterruptedException {  
        ThreadA threadA =new ThreadA();  
        ThreadB threadB=new ThreadB();  
        ThreadC threadC = new ThreadC();  
        threadA.start();  
        threadB.start();  
        threadC.start();  
        threadC.join();//让C线程执行完后在输出cout值否则可能cout在ABC线程都未完成时就输出结果。  
        System.out.println(count);  
    }  
}  
