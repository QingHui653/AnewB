package test.core.thread;

class MyThread4 implements Runnable{
	public void run() {
		while(true)
			System.out.println(Thread.currentThread().getName() + "在运行");
	}
	
}

/**
 * 前台线程结束了，后台线程仍然可以继续运行。使用方法：setDaemon(true)
 * @author woshizbh
 *
 */
public class ThreadDaemonDemo {
	public static void main(String[] args) {
		Thread t = new Thread(new MyThread4(),"线程");
		t.setDaemon(true);  //此方法需要放在start方法之前才会生效
		t.start();	
	}

}
