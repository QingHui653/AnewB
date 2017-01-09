package test.base;

public class StaticTest {
	private static String LOCAL="静态变量";
	
	static{
		System.out.println("静态代码执行了！");
	}
	
	public void sayHello() {
		System.out.println("hello");
	}
	
	public static void main(String[] args) {
		StaticTest staticTest =new StaticTest();
		System.out.println(StaticTest.LOCAL);
		staticTest.sayHello();
		
	}
}
