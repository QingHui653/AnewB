package test.thread;

/**
 * 表示一个网站的基本信息
 * */
class Info {
	private String name;
	private String url;
	private boolean flag = false; // 标记赋值状态，false表示还未赋值

	/**
	 * 赋值
	 * */
	public synchronized void setValues(String name, String url) {
		// 如果已被赋值，等待
		if (flag) {
			try {
				this.wait(); // 已经赋值而且值未被使用，则一直等待被使用
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.name = name;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.url = url;

		flag = true; // 赋值之后，更新标记状态
		this.notify(); // 赋值之后，唤醒等待取值的线程

	}

	/**
	 * 取值
	 * */
	public synchronized void getValues() {
		// 如果未被赋值，等待
		if (!flag) {
			try {
				this.wait(); // 上一次赋的值已经使用过，下一次的还未生产
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("站点：" + name + " --> URL：" + url);
		flag = false;
		this.notify(); // 唤醒等待赋值的线程

	}
}

/**
 * 生产者，负责给参数赋值
 * */
class Producer implements Runnable {
	private Info info = null;

	public Producer(Info info) {
		this.info = info;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			// 交替赋值
			if (i % 2 == 0)
				info.setValues("zifangsky的个人博客", "https://www.zifangsky.cn");
			else
				info.setValues("51CTO博客", "http://blog.51cto.com");
		}
	}
}

/**
 * 消费者，负责取值
 * */
class Consumer implements Runnable {
	private Info info = null;

	public Consumer(Info info) {
		this.info = info;
	}

	public void run() {
		for (int i = 0; i < 10; i++)
			info.getValues();
	}
}

/**
 * 要求是：生产者生产出一个产品后消费者才能消费，否则等待，一直到生产者生产出产品后将消费者唤醒；反之亦然。关于等待和唤醒分别是这两个方法：wait()和notify()。代码如下：
 * 生产者与消费者demo
 * @author woshizbh
 *
 */
public class Demo {
	public static void main(String[] args) {
		Info info = new Info();
		Producer producer = new Producer(info);
		Consumer consumer = new Consumer(info);

		new Thread(producer).start();
		new Thread(consumer).start();

	}

}

