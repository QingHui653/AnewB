package test.core.thread.demo;

/**
 * 
 * @author woshizbh
 *
 */
@Deprecated
class MyThread8 implements Runnable {
	private int ticket = 100;

	public void run() {
		// 添加同步代码块
		while (ticket > 0) {
			synchronized (this) {
				if (ticket > 0) {

					System.out.println(Thread.currentThread().getName()
							+ "卖票，剩余票数： ticket = " + (ticket - 1));
					ticket--;
				}
			}
		}
	}
}

@Deprecated
//错误 只输出: 一种售票员卖票
//理想情况是 ABC随机出现. 售票不小于0
public class SyncDemo2 {
	public static void main(String[] args) {
		MyThread8 mThread8 = new MyThread8();
		Thread t1 = new Thread(mThread8, "售票员A");
		Thread t2 = new Thread(mThread8, "售票员B");
		Thread t3 = new Thread(mThread8, "售票员C");

		t1.start();
		t2.start();
		t3.start();

	}
}
