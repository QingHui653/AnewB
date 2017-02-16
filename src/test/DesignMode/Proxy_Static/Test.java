package test.DesignMode.Proxy_Static;
/**
 * 静态代理模式
 * @author woshizbh
 *
 */
public class Test {
	 public static void main(String[] args) {
	  ProxyInterface proxyInterface = new WeddingCompany(new NormalHome());
	  proxyInterface.marry();
	 }
	}
