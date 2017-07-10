package test.core.Amd;

public class TryTest {
	public static void main(String[] args) {

		System.out.println(new TryTest().test());
		;
	}

	static int test() {
		int x = 1;
		try {
			x = 2;
			return x;
		}catch(Exception e){
			x=3;
			return x;
		} finally {
			x = 4;
			//finally return会提前退出，不是正常的返回值
//			return x;
		}
	}
}
