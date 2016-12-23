package test.DesignMode.Proxy_Static;

public class Test {
	 public static void main(String[] args) {
	  ProxyInterface proxyInterface = new WeddingCompany(new NormalHome());
	  proxyInterface.marry();
	 }
	}
