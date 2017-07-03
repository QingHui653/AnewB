package test.core.DesignMode.Singleton;

/**
 * 单例模式(懒汉模式 线程不安全)
 * @author woshizbh
 *
 */
public class Singleton1 {

	 private static Singleton1 singleton;

	 private Singleton1() {
	 }
	 //保证 线程安全直接加锁即可，但影响效率
	 public static /*synchronized*/ Singleton1 getInstance() {
	  if (singleton == null) {
		  singleton = new Singleton1();
	  }
	  return singleton;
	 }
	}
