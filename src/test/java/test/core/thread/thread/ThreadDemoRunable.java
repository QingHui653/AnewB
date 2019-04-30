package test.core.thread.thread;

public class ThreadDemoRunable{

	public static void main(String[] args) {
		Demo2 demo1 = new Demo2("zifangsky");
		Demo2 demo2 = new Demo2("admin");
		
		Thread thread1 = new Thread(demo1);
		thread1.start();
		Thread thread2 = new Thread(demo2);
		thread2.start();
	}

}

class Demo2 implements Runnable {
	private String name;
	
	public Demo2(String name) {
		this.name = name;
	}

	public void run() {
		System.out.println("Hi," + name + "欢迎你的访问");

	}
}
