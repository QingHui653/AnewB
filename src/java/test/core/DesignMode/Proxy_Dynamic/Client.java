package test.core.DesignMode.Proxy_Dynamic;

public class Client {  
	  
    public static void main(String[] args) {  
        LogHandler logHandler = new LogHandler();  
        UserManager userManager = (UserManager)logHandler.newProxyInstance(new UserManagerImpl());  
        userManager.findUser("0001");  
    }  
} 
