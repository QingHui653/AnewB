package test.core.thread;

/**
 * 退出demo
 * 三种退出方式
 * @author woshizbh
 *
 */

public class ExitDemo implements Runnable {
	
	public volatile boolean exit = false; 
	
	public void sys(int i){
		System.out.println("   --"+i);
	}
	@Override
	public void run() {
		//第一种使用一个boolean变量来判断
		//2. 使用stop方法终止线程虽然使用上面的代码可以终止线程，但使用stop方法是很危险的
		//3使用interrupt方法来终端线程可分为两种情况：
	    //（1）线程处于阻塞状态，如使用了sleep方法。
	    //（2）使用while（！isInterrupted（））{……}来判断线程是否被中断。
	    //在第一种情况下使用interrupt方法，sleep方法将抛出一个InterruptedException例外，而在第二种情况下线程将直接退出
		while (!exit){
			System.out.println("1");
		};
		System.out.println("runalbe!stop");
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		ExitDemo e1= new ExitDemo();
		Thread thread1 =new Thread(e1);
		/**
		 * 1.start（）方法来启动线程，真正实现了多线程运行。
		 * 2.run（）方法当作普通方法的方式调用。
		 */
		thread1.start();
		thread1.sleep(500);
		e1.exit = true;  // 终止线程thread  
        System.out.println("线程退出!"); 
	}
}	
