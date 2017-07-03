package test.core.thread.more;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
	
	//main
	public static void main(String[] args) {
		ThreadTest t =new ThreadTest();
		t.thread();
	}
	
	
	private volatile int count =2;
	
	private Lock lock = new ReentrantLock();
	
	public void thread() {
		ExecutorService excutor = Executors.newCachedThreadPool();
		ThreadTest t = new ThreadTest();
		
		//新开三个线程
		for (int j = 1; j <= 3; j++) {
			excutor.execute(new Runnable() {
				
				//线程任务
				@Override
				public void run() {
					//已经开始线程了
					t.threadPrint();
					//在这进行lock操作，传参
					/*if(lock==false){  错误
						lock=true;
						String fileName = "C:\\Users\\woshizbh\\Desktop\\"+count+".txt";
						t.threadPrint(fileName);
						count++;
					}
					lock=false;*/
				}
			});
		}
		excutor.shutdown();
	}
	
	
	
	
	//每个线程进行的操作，都是单独的
	public void threadPrint() {
		BufferedWriter writer;
		String fileName;
		
		lock.lock();
		fileName = "C:\\Users\\woshizbh\\Desktop\\"+count+".txt"; // 文件名
		count++;
		lock.unlock();
		
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
	
