package test.thread;

class MyThread9 implements Runnable {
	private int ticket = 100;

	public void run() {
		sale();
	}

	/**
	 * 同步方法
	 * */
	public synchronized void sale() {
		while (ticket > 0) {
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			System.out.println(Thread.currentThread().getName()
					+ "卖票，剩余票数： ticket = " + --ticket);
		}

	}
}

/**
 * 这个例子在这里并不合适，因为同步方法的原因导致整个卖票过程都在一个线程里执行了，因此在这里多线程的作用并没有体现出来
 * @author woshizbh
 *
 */
public class SyncDemo3 {
	public static void main(String[] args) {
		MyThread9 mThread9 = new MyThread9();
		Thread t1 = new Thread(mThread9, "售票员A");
		Thread t2 = new Thread(mThread9, "售票员B");
		Thread t3 = new Thread(mThread9, "售票员C");

		t1.start();
		t2.start();
		t3.start();

	}

}
