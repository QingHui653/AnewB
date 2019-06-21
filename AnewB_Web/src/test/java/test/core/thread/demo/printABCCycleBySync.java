package test.core.thread.demo;


public class printABCCycleBySync implements Runnable {
	
	private Boolean exist=false;
	private String name;
	private Object first;
	private Object last;
	
	public printABCCycleBySync() {
	}
	
	public printABCCycleBySync(String name, Object first, Object last) {
		this.name=name;
		this.first=first;
		this.last=last;
	}
	
	@Override
	public void run() {
		while (!exist) {
			synchronized (first) {
				synchronized (last) {
						System.out.println(name);
						last.notify();
				}
				try {
					first.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		Thread A =new Thread(new printABCCycleBySync("A",c,a));
		Thread B =new Thread(new printABCCycleBySync("B",a,b));
		Thread C =new Thread(new printABCCycleBySync("C",b,c));
		A.start();
		A.sleep(1000);
		B.start();
		B.sleep(1000);
		C.start();
		C.sleep(1000);
	}
}
