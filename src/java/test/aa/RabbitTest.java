package test.aa;

import org.junit.Test;

/**
 * 算兔子 算法，
 * 有一只兔子从第三与开始生一只小兔子
 * @author woshizbh
 *
 */
public class RabbitTest {
	
	//递归
	public int rabbit(int month) {
		return month==0?0:(month==1||month==2?1:rabbit(month-1)+rabbit(month-2));
	}
	
	@Test
	public void test() {
		System.out.println(rabbit(3));
	}
}
