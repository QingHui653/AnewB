package test.core.exceptio;

public class FinallyTest {
	
	public static void main(String[] args) {
		FinallyTest finallyTest = new FinallyTest();
		int y= finallyTest.method(1);
		int y2= finallyTest.method2(1);
		System.out.println(y);
		System.out.println(y2);
	}
	
	public int method(int x) {
		int y=x;
		try {
			//这里仅仅需要注意的是在try{}语句中执行到return 1会在临时栈中存储值为1的变量。
			//接着回去执行finally里面的内容，这时执行finally中的return 2;方法，这时临时栈中的值就是变为 2，
			//会覆盖原来临时栈中的值1.所以它的返回值为2。
			return y;
		} catch (Exception e) {
			return y;
		} finally {
			y++;
			System.out.println("finally在renturn后执行了");
		}
	}
	
	@SuppressWarnings("finally")
	public int method2(int x) {
		int y=x;
		try {
			return y;
		} catch (Exception e) {
			return y;
		} finally {
			System.out.println("finally在renturn后执行了");
			y++;
			return y;
		}
	}
}
