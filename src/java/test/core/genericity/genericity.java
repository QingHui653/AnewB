package test.core.genericity;

import java.util.Arrays;

/**
 * 泛型
 * @author woshizbh
 *
 */
public class genericity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genericity g = new genericity();
		Integer[] t = g.genericity(5, 4);
		String[] s = g.genericity("5", "4");
		System.out.println("---int " + Arrays.toString(t));
		System.out.println("---string " + Arrays.toString(s));

	}

	public <T> T[] genericity(T... t) {
		return t;
	}

}
