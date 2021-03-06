package test.core.thread.demo;

/**
 * 多线程操作数据未保证可见性，原子性，无法保证对数据操作正常
 * @author woshizbh
 *
 */

class MyThread7 implements Runnable{
	private int ticket = 10;  //多个线程共享
	public void run(){
		while(ticket > 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"卖票，剩余票数： ticket = " + --ticket);
		}
	}
}

@Deprecated
//错误: 售票 <0
//理想情况是 ABC随机出现. 售票不小于0
public class SyncDemoError {
	public static void main(String[] args) {
		MyThread7 mThread7 = new MyThread7();
		Thread t1 = new Thread(mThread7,"售票员A");
		Thread t2 = new Thread(mThread7,"售票员B");
		Thread t3 = new Thread(mThread7,"售票员C");
		
		t1.start();
		t2.start();
		t3.start();

	}

}
