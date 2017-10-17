package test.core.DesignMode.Singleton;

/**
 * 双重校验锁
 * 
 * @author woshizbh
 * 
 */
public class Singleton5 {
	private volatile static Singleton5 singleton5;

	private Singleton5() {
	}

	public static Singleton5 getSingleton() {
		if (singleton5 == null) {
			synchronized (Singleton5.class) {
				if (singleton5 == null) {
					singleton5 = new Singleton5();
				}
			}
		}
		return singleton5;
	}
}
