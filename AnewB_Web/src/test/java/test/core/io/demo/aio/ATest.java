package test.core.io.demo.aio;

import java.util.Scanner;

import test.core.io.demo.aio.client.AClient;
import test.core.io.demo.aio.server.AServer;  
/** 
 * 测试方法 
 */  
public class ATest {  
    //测试主方法  
    @SuppressWarnings("resource")  
    public static void main(String[] args) throws Exception{  
        //运行服务器  
        AServer.start();  
        //避免客户端先于服务器启动前执行代码  
        Thread.sleep(100);  
        //运行客户端   
        AClient.start();  
        System.out.println("请输入请求消息：");  
        Scanner scanner = new Scanner(System.in);  
        while(AClient.sendMsg(scanner.nextLine()));  
    }  
}  
