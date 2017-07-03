package test.core.thread.more;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//rwl.writeLock().lock();
//在写锁状态中，可以获取读锁
//rwl.readLock().lock();
//rwl.writeLock().unlock();


//rwl.readLock().lock();
//......
//必须释放掉读锁，才能够加写锁
//rwl.readLock().unlock();
//rwl.writeLock().lock();
class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 
    void processCachedData() {
        rwl.readLock().lock();
        /* 初始化变量，需要写入数据 */
        if (!cacheValid) {
            // 在获取写锁前释放读锁
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                /* 重新检查状态，因为其它线程很可能在这之前获取了写锁，并改写了数据 */
                if (!cacheValid) {
                    System.out.println(Thread.currentThread().getName()
                            + ": 缓存未初始化");
                    data = getData();
                    cacheValid = true;
                    System.out.println(Thread.currentThread().getName()
                            + ": 缓存初始完成，当前值：" + data);
                }
                /* 降级锁：在释放写锁前获取读锁 */
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); /* 释放写锁，依然保持读锁 */
            }
        }
        /* 此时，依然存在读锁 */
        try {
            System.out.println(Thread.currentThread().getName() + ": 获取缓存值为："+ data);
        } finally {
            /* 释放读锁 */
            rwl.readLock().unlock();
        }
    }
    int getData(){
        return new Random().nextInt(100000);
    }
}
 
public class LockRWDemo {
    public static void main(String[] args) {
        final CachedData cachedData = new CachedData();
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    cachedData.processCachedData();
                }
            }.start();
        }
    }
}
