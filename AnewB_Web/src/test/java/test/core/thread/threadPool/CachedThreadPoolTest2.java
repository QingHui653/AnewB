package test.core.thread.threadPool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// 新开 三个 线程 写入不同文件
public class CachedThreadPoolTest2 {
	
	//main
	public static void main(String[] args) {
		CachedThreadPoolTest2 t =new CachedThreadPoolTest2();
		t.thread();
	}
	
	//保证可见性 无法保证原子性
	private volatile int count =2;
	
	//自旋锁
	private volatile boolean caslock = false;
	
	public  AtomicInteger inc = new AtomicInteger(2);
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void thread() {
		ExecutorService excutor = Executors.newCachedThreadPool();
		CachedThreadPoolTest2 t = new CachedThreadPoolTest2();
		
		//新开三个线程
		for (int j = 1; j <= 3; j++) {
			excutor.execute(new Runnable() {
				
				//线程任务
				@Override
				public void run() {
					//已经开始线程了
					//在这进行lock操作，传参
					String fileName="C:\\Users\\b\\Desktop\\null.txt";
					if(caslock==false){
						caslock=true;
						fileName = "C:\\Users\\b\\Desktop\\"+inc+".txt";
						caslock=false;
						inc.incrementAndGet();
					}
					t.threadPrint(fileName);
				}
			});
		}
		excutor.shutdown();
	}
	
	
	
	
	//每个线程进行的操作，都是单独的
	public void threadPrint(String fileName) {
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(new File(fileName), true));
			System.out.println("-- "+fileName);
			for (int i = 0; i < 1000; i++) {
				String temp = Thread.currentThread().getName() + ": " + i + "----------------------------------我是一条华丽的小尾巴";
				writer.write(temp);
                writer.newLine();
                writer.flush();
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println(Thread.currentThread().getName());
		}
		
	}
	
}	
	
