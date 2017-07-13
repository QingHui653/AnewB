package test.core.io.demo.bio;

import java.io.IOException;
import java.util.Random;

/**
 * 堵塞式IO如果有一个
 * 地方堵塞会导致所有的流程都不能往下走
 * 代码的bug会导致的client或server堵塞
 * @author woshizbh
 *
 */
public class Test {  
    //测试主方法  
    public static void main(String[] args) throws InterruptedException {  
        //运行服务器  
        new Thread(new Runnable() {  
            @Override  
            public void run() {  
                try {  
                    BServerBetter.start();  
//                	ServerNormal.start();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }).start();  
        //避免客户端先于服务器启动前执行代码  
        Thread.sleep(100);  
        //运行客户端   
        char operators[] = {'+','-','*','/'};  
        Random random = new Random(System.currentTimeMillis());  
        new Thread(new Runnable() {  
            @SuppressWarnings("static-access")  
            @Override  
            public void run() {  
                while(true){  
                    //随机产生算术表达式  
                    String expression = random.nextInt(10)+""+operators[random.nextInt(4)]+(random.nextInt(10)+1);  
                    BClient.send(expression);  
                    try {  
                        Thread.currentThread().sleep(1000);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }).start();  
    }  
} 
