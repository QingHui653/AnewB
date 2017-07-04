package test.core.javacase;

import org.junit.Test;

/**
 * 算兔子 算法，
 * 有一只兔子从第三与开始生一只小兔子
 * @author woshizbh
 */
/**
 * 可以优化，因为每次是从1开始算。1 1 2 3 5 8
 * 应该将最后一个数记录下来，当做缓存文件，下次直接取缓存文件计算
 * @author woshizbh
 */
public class RabbitTest {
	
	//递归
	public int rabbit(int month) {
		return month==0?0:(month==1||month==2?1:rabbit(month-1)+rabbit(month-2));
	}
	
	public int rabbit2(int month) {
		if(month==0)
			return 0;
		if(month==1||month==2)
			return 1;
		return rabbit2(month-1)+rabbit2(month-2);
	}
	
	@Test
	public void test() {
		System.out.println(rabbit(6));
	}
}
