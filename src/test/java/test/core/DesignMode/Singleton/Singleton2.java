package test.core.DesignMode.Singleton;

/**
 * 单例模式(饿汉模式 线程安全,通常使用这种)
 * @author woshizbh
 *
 */
public class Singleton2 {  
    private static Singleton2 instance = new Singleton2();  
    
    private Singleton2 (){}  
    
    public static Singleton2 getInstance() {  
    	return instance;  
    }  
}
