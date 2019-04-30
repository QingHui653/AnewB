package test.core.thread.thread;

class MyThread3 implements Runnable{

	public void run(){
		System.out.println("1 run方法开始执行");
		try {
			Thread.sleep(10000);
			System.out.println("2 休眠结束");
		} catch (InterruptedException e) {
			System.out.println("3 休眠被终止");
			return;
		}
		System.out.println("4 run方法正常结束");	
	}
}

/**
 * 当一个线程运行时，在其他线程中可以中断该线程的运行状态。使用方法：interrupt()
 * @author woshizbh
 *
 */
public class ThreadInterruptDemo {

	public static void main(String[] args) {
		Thread thread = new Thread(new MyThread3());
		thread.start();  //线程启动
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();  //主线程暂停2s之后将其中断
	}

}
