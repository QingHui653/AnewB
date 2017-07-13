package test.core.io.demo.nio;

public class NServer {
	
	private static int DEFAULT_PORT = 12345;  
    private static NServerHandle serverHandle; 
    
    public static void start(){  
        start(DEFAULT_PORT);  
    }
    
    public static synchronized void start(int port){  
        if(serverHandle!=null)  
            serverHandle.stop();  
        serverHandle = new NServerHandle(port);  
        new Thread(serverHandle,"Server").start();  
    } 
    
    public static void main(String[] args){  
        start();  
    } 
}
