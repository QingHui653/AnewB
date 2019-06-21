package test.core.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther:woshizbh
 * @Date: 2019/4/30
 * @Deseription
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程"+Thread.currentThread().getName());
            }
        });
        for (int i=0;i<5;i++) {
            new Task(barrier).start();
        }
    }

    static class Task extends Thread {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public void run() {
            System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
            try {
                Thread.sleep(3000);
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("全部线程执行完成");
        }
    }
}
