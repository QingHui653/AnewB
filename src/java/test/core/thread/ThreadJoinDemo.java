package test.core.thread;

class MyThread2 implements Runnable{

	public void run(){
		for(int i=0;i<6;i++)
			System.out.println("正在运行线程：" + Thread.currentThread().getName() + " --> " + i);
	}
}
/**
 * 在线程操作中，可以使用join()方法让一个线程强制启动。该线程强制启动期间，一直到该线程运行结束其他线程都不能运行，必须等待该线程结束之后才能继续运行
 * @author woshizbh
 *
 */
public class ThreadJoinDemo {
	public static void main(String[] args) {
		MyThread2 demo = new MyThread2();
		Thread thread = new Thread(demo, "线程");
		thread.start();
		for(int i=0;i<6;i++){
			System.out.println("Main线程运行 --> " + i);
			if(i > 2){
				try {
					/**
					 * 线程强制运行
					 * 线程强制运行期间，其他线程无法运行，必须等待此线程完成之后才可以继续执行
					 * */
					thread.join();  
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
