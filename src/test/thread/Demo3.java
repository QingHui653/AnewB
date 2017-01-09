package test.thread;

class Computer{
	private String name;
	private boolean status = false;  //标记computer状态，false表示还未生产
	private int sum = 0;

	public synchronized String getName() {
		if(!status){
			//未生产，等待电脑生产
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		status = false;
		notify();
		return name;
	}

	public synchronized void setName(String name) {
		if(status){
			/**
			 * 如果生产的电脑没有卖出，则要等待电脑卖出之后再生产，
			 * 并统计出生产的电脑数量
			 * */
			System.out.println("到目前为止，一共生产出了 " + sum + " 台电脑");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.name = name;
		sum++;
		status = true;
		notify();
	}
	
}
/**
 * 生产电脑
 * */
class ComputerProducer implements Runnable{
	Computer computer = null;
	
	public ComputerProducer(Computer computer) {
		this.computer = computer;
	}

	public void run() {
		for(int i=0;i<10;i++)
			computer.setName("zifangsky00" + i);	
	}
	
}
/**
 * 卖电脑
 * */
class ComputerConsumer implements Runnable{
	Computer computer = null;
	
	public ComputerConsumer(Computer computer) {
		this.computer = computer;
	}

	public void run() {
		for(int i=0;i<10;i++)
			System.out.println("卖出电脑，编号是： " + computer.getName());	
	}
	
}

/**
 * 消费者与生产者demo
 * @author woshizbh
 *
 */
public class Demo3 {

	public static void main(String[] args) {
		Computer computer = new Computer();
		ComputerProducer producer = new ComputerProducer(computer);
		ComputerConsumer consumer = new ComputerConsumer(computer);
		//生产者
		new Thread(producer).start();
		//消费者
		new Thread(consumer).start();

	}

}
