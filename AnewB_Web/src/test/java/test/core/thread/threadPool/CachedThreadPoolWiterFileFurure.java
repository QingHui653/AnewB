package test.core.thread.threadPool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在JDK1.5以前的线程是没有返回值的（Thread,Runnable），Callable这个接口是之后才出现的新特性，用法跟Runnable类似，只是不同的是可以有返回值。因此为了测试Callable这个类以及线程池相关内容，我将上一篇文章中的代码进行了小幅度的修改然后写了一下
二 关于线程池的简单使用步骤
1 定义线程类，（1）extends Thread （2）implements Runnable （3）implements Callable<> 
2 建立ExecutorService线程池，比如这样写：ExecutorService pool = Executors.newCachedThreadPool(); 这是建立一个缓存池。一般常用的还有：
（1）newFixedThreadPool() （2）ScheduledThreadPool() （3）newSingleThreadExecutor()
newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
3 调用线程池进行操作
//执行任务并获取Future对象 
Future<List<String>> future = pool.submit(myThread);
//从Future对象上获取任务的返回值
future.get();
 * @author woshizbh
 *
 */
//使用线程池写入文件
public class CachedThreadPoolWiterFileFurure {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long millis1 = System.currentTimeMillis();
		String fileName = "C:\\Users\\b\\Desktop\\测试3.txt";
		File f=new File(fileName);
    	if(f.exists()){f.createNewFile();}
		int threadNum = 5;  //测试用的线程数目
		List<String> list = new ArrayList<String>();
		
		//创建一个缓存线程池
		ExecutorService pool = Executors.newCachedThreadPool();
//		ExecutorService pool = Executors.newWorkStealingPool();
		for (int i = 0; i < threadNum; i++) {
			// 1.通过构造方法
			Callable<List<String>> myThread = new Thread2("线程" + i, i, threadNum);
			// 2. 通过设置参数
			((Thread2) myThread).setFileName("第二种");
			// 3. new Thread2( new Work());
			// Thread2 内 要调用 new Work 的方法来修改 参数
			Future<List<String>> future = pool.submit(myThread);//submit执行callable  execute执行runable
			try {
				list.addAll(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();  //不关闭的话，要等到超时程序才会结束
		
		Iterator<String> iterator = list.iterator();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
			while(iterator.hasNext()){
				writer.write(iterator.next());
				writer.newLine();
				writer.flush();
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long millis2 = System.currentTimeMillis();
		System.out.println(millis2 - millis1);  //大约170-177ms
	}

}

/**
 * 自定义任务（线程）
 * */
class Thread2 implements Callable<List<String>>{
	private String name; // 线程的名字
	private int i; // 第几个线程
	private int threadNum; // 总共创建了几个线程

	private String fileName;

	public Thread2(String name, int i, int threadNum) {
		this.name = name;
		this.i = i;
		this.threadNum = threadNum;
	}

	public List<String> call() throws Exception {
		MyPrint2 myPrint2 = new MyPrint2();
		return myPrint2.print(name, i, threadNum);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

/**
 * 具体的业务操作
 * */
class MyPrint2 {

	public List<String> print(String name, int x, int threadNum) {
		List<String> list = new ArrayList<String>();

		for (int i = x; i <= 10000; i = i + threadNum) {
//			System.out.println(name + ": " + i + "----------------------------------我是一条华丽的小尾巴");
			list.add(name + ": " + i + "----------------------------------我是一条华丽的小尾巴");
		}		
		return list;
	}

}

