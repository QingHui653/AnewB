package test.core.thread.thread;

class MyThread5 implements Runnable{

	public void run(){
		for(int i=0;i<3;i++){
			/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			System.out.println("正在运行线程：" + Thread.currentThread().getName() + " --> " + i);
		}
	}
}

/**
 * （4）线程的优先级
在Java中，线程一共有3种优先级，分别是：

定义	描述	表示的常量
public static final int MIN_PRIORITY	线程可以具有的最低优先级	1
public static final int NORM_PRIORITY	分配给线程的默认优先级	5
public static final int MAX_PRIORITY	线程可以具有的最高优先级	10
从上面的示例可以看出，主线程的优先级是5，也就是默认的优先级。题外话：Java程序每次运行至少需要启动几个线程？答案是2，一个是main（主）线程，另一个是垃圾回收线程
 * @author woshizbh
 *
 */
public class ThreadPriorityDemo {

	public static void main(String[] args) {
		System.out.println("主方法的线程优先级： " + Thread.currentThread().getPriority());
		
		Thread t1 = new Thread(new MyThread5(), "线程1");
		Thread t2 = new Thread(new MyThread5(),"线程2");
		Thread t3 = new Thread(new MyThread5(),"线程3");
		t1.setPriority(Thread.MIN_PRIORITY);  // 1
		t2.setPriority(Thread.NORM_PRIORITY);  // 5
		t3.setPriority(Thread.MAX_PRIORITY);  // 10
		t1.start();
		t2.start();
		t3.start();
	}

}