package test.thread.more;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pan {
    private Lock lock = new ReentrantLock(); 
    /*烹饪方法，该方法输出步骤*/
    public void Cook(String[] steps) {
         lock.lock();
         try{
            for (int i = 0; i < steps.length; i++) {
                /*模拟竞争造成的线程等待，这样效果明显些*/
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(steps[i]);
            }
            System.out.println("");
         }finally{
             /*
             * synchronized是在JVM层面上实现的，不但可以通过一些监控工具监控synchronized的锁定，
             * 而且在代码执行出现异常时，JVM会自动释放锁定。
             * 但是使用Lock则不行，lock是通过代码实现的。
             * 要保证锁定一定会被释放，就必须将unLock()放到finally{}中
             */
            lock.unlock();
         }
 
    }
    /*青椒炒肉制作步骤：a1.放肉，a2.放盐，a3.放辣椒  a4 a5....*/
    String[] steps_LaJiaoChaoRou={"a1.","a2.","a3.","a4.","a5.","a6.","a7.","a8.","a9.","a10.","OK：辣椒炒肉"};
    /*番茄炒蛋制作步骤：b1.放蛋，b2.放盐，b3.放番茄*/
    String[] steps_FanQieChaoDan={"b1.","b2.","b3.","b4.","b5.","b6.","OK:番茄炒蛋"};
}
 
public class LockDemo {
    public static void main(String[] args) {
 
        final Pan pan=new Pan();
        /*线程1：老大炒青椒炒肉。*/
        new Thread(){
            public void run() {
                /*为了看出错乱效果，这里用死循环，一段时间后手工点击停止运行按钮*/
                while (true) {
                    try {
                        /*青椒炒肉需要5秒;*/
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pan.Cook(pan.steps_LaJiaoChaoRou);
                }
            }
        }.start();
 
        /*线程2：老二炒番茄炒蛋。*/
        new Thread(){
            public void run() {
                /*为了看出错乱效果，这里用死循环，一段时间后手工点击停止运行按钮*/
                while (true) {
                    try {
                        /*番茄炒蛋需要5秒;*/
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pan.Cook(pan.steps_FanQieChaoDan);
                }
            }
        }.start();
    }
}
