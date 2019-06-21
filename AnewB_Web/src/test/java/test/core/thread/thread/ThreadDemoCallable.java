package test.core.thread.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemoCallable {
	 
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	List<String> list = new ArrayList<String>();
    	
        Callable<String> demo1 = new Demo4("zifangsky");
        Callable<String> demo2 = new Demo4("admin");
        
        FutureTask<String> Task1 = new FutureTask<String>(demo1);
        FutureTask<String> Task2 = new FutureTask<String>(demo2);
        Thread Thread1 = new Thread(Task1);
        Thread Thread2 = new Thread(Task2);
        Thread1.start();
        Thread2.start();
        String t1= (String) Task1.get();
        String t2= (String) Task2.get();
        System.out.println(t1);
        System.out.println(t2);
    }
 
}
 
class Demo4 implements Callable<String>{
	
	private String name;
	
	public Demo4(String name) {
		this.name = name;
	}
	
	@Override
	public String call() throws Exception {
        System.out.println(name+"正在执行");
        if(name.equals("admin")) Thread.currentThread().sleep(1000);
		return name+"执行完毕!";
	}
    
}
