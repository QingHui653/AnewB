package test.core.thread.thread;

class MyThread6 implements Runnable{

	public void run(){
		for(int i=0;i<6;i++){			
			System.out.println("正在运行线程：" + Thread.currentThread().getName() + " --> " + i);
			
			if(i == 3){
				System.out.print(Thread.currentThread().getName() + "进行礼让： ");
				Thread.currentThread().yield();
			}
		}
	}
}

/**
 * 在线程操作中，可以使用yield()方法让一个线程将执行权暂时让给另一个线程
 * @author woshizbh
 *
 */
public class ThreadYieldDemo {
	public static void main(String[] args) {
		MyThread6 mThread6 = new MyThread6();
		Thread t1 = new Thread(mThread6, "线程A");
		Thread t2 = new Thread(mThread6, "线程B");
		
		t1.start();
		t2.start();
	}

}

