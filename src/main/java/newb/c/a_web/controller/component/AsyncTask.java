package newb.c.a_web.controller.component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
/*Async配置 https://www.cnblogs.com/zhengbin/p/6104502.html*/
/*配置了 Async报错 https://my.oschina.net/tridays/blog/805111*/
@Service
@Slf4j
public class AsyncTask {

    private Random random =new Random();

    @Async()
	public Future<String> doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一OK");
    }

    @Async("mySimpleAsync")
    public Future<String> doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二OK");
    }

    @Async("myAsync")
    public Future<String> doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三OK");
    }

    @Async("myAsync")
    public Future<String> doTask(CountDownLatch countDownLatch,Integer i) throws Exception {
        log.info("开始做任务三"+i);
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务"+i+"，耗时：" + (end - start) + "毫秒");
        countDownLatch.countDown();
        return new AsyncResult<>("任务"+i+"OK");
    }
    
    //CompletableFuture jdk 1.8自带
    @Async
    public CompletableFuture<String> doTaskFour(String firstname) throws InterruptedException{
        log.info("开始做任务四");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务四，耗时：" + (end - start) + "毫秒");
    	return CompletableFuture.completedFuture("任务CompletableFuture OK");
    }
    
    //ListenableFuture spring自带
    @Async
    public ListenableFuture<String> doTaskFive(String firstname) throws InterruptedException{
        log.info("开始做任务五");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务五，耗时：" + (end - start) + "毫秒");
    	return new AsyncResult<>("任务五OK");
    }
}
