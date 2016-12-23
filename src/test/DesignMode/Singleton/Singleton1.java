package test.DesignMode.Singleton;

/**
 * 单例模式(懒汉模式 线程不安全)
 * @author woshizbh
 *
 */
public class Singleton1 {

	 private static Singleton1 singleton;

	 private Singleton1() {
	 }

	 public static Singleton1 getInstance() {
	  if (singleton == null) {
	   singleton = new Singleton1();
	  }
	  return singleton;
	 }
	}
