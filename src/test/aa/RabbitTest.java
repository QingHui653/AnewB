package test.aa;

import org.junit.Test;

public class RabbitTest {
	
	public int rabbit(int month) {
		return month==0?0:(month==1||month==2?1:rabbit(month-1)+rabbit(month-2));
	}
	
	@Test
	public void test() {
		System.out.println(rabbit(6));
	}
}
