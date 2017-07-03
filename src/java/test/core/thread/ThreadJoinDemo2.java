package test.core.thread;

public class ThreadJoinDemo2 {
	  public static void main(String args[]) {
		  MyThreadJoin2 thread2 = new MyThreadJoin2("mythread");
	    // 在创建一个新的线程对象的同时给这个线程对象命名为mythread
	    thread2.start();// 启动线程
	    try {
	      thread2.join();// 调用join()方法合并线程，将子线程mythread合并到主线程里面
	      // 合并线程后，程序的执行的过程就相当于是方法的调用的执行过程
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    for (int i = 0; i <= 5; i++) {
	      System.out.println("I am main Thread");
	    }
	  }
	}

	class MyThreadJoin2 extends Thread {
	  MyThreadJoin2(String s) {
	    super(s);
	    // 使用super关键字调用父类的构造方法 父类Thread的其中一个构造方法：“public Thread(String name)”
	    // 通过这样的构造方法可以给新开辟的线程命名，便于管理线程
	  }

	  public void run() {
	    for (int i = 1; i <= 5; i++) {
	      System.out.println("I am a\t" + getName());
	      // 使用父类Thread里面定义的
	      // public final String getName()，Returns this thread's name.
	      try {
	        sleep(100);// 让子线程每执行一次就睡眠1秒钟
	      } catch (InterruptedException e) {
	        return;
	      }
	    }
	  }
	}