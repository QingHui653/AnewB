package test.core.io.demo.nio;

import java.util.Scanner;

public class Test {  
    //测试主方法  
    public static void main(String[] args) throws Exception{  
        //运行服务器  
        NServer.start();  
        //避免客户端先于服务器启动前执行代码  
        Thread.sleep(100);  
        //运行客户端   
        NClient.start();  
        while(NClient.sendMsg(new Scanner(System.in).nextLine()));  
    }  
}
