package test.core.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutiThreadShareData {

    private static MutiShareData mutiShareData = new MutiShareData();

    public static void main(String[] args) {
    	
    	//开启三个线程+1
        for(int i=0;i<3;i++){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread()+":{j from "+ mutiShareData.getJ()+" + to: "+mutiShareData.increment()+"}");
                        }
                    }
            ).start();
        }
        
      //开启两个线程-1
        for(int i=0;i<2;i++){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread()+":{j from "+ mutiShareData.getJ()+" - to: "+mutiShareData.decrement()+"}");
                        }
                    }
            ).start();
        }
    }

}

/**
 * 将共享数据封装在另一对象中（操作数据的方法也在该对象完成）
 */
class MutiShareData{
    private volatile int j = 0;
    
    //使用同步
    public synchronized  int increment(){
        return  ++j;
    }
    public synchronized int  decrement(){
        return --j;
    }

    public synchronized int getJ() {
        return j;
    }

    public synchronized void setJ(int j) {
        this.j = j;
    }
    
    /*Lock lock =new ReentrantLock();
    Lock lock2 =new ReentrantLock();
    
    public int increment(){
    	try {
    		lock.lock();
    		return  ++j;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return 0;
        
    }
    public int  decrement(){
    	try {
    		lock.lock();
    		return  --j;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return 0;
    }

    public int getJ() {
    	try {
    		lock.lock();
    		return  j;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return j;
    }
    
    public void setJ() {
    	try {
    		lock.lock();
    		this.j=j;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
    }*/
}
