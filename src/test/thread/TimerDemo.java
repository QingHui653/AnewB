package test.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
	static int count = 0;
	public static void main(String[] args) {
		class MyTimerTask extends TimerTask{

	        @Override
	        public void run() {
	            System.out.println(Thread.currentThread()+" bomb!");
	            new Timer().schedule(new MyTimerTask(), 2000+1000*(count++%2));
	        }
	    }
		
	    //3s后开启定时器
	    new Timer().schedule(new MyTimerTask(),3000);
	}




}
