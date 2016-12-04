package test;

import org.junit.Test;

public class StringTest {


	@Test
	public void test() {
		String foo="1";
		String bar=foo;
		foo="2"+1;
		System.out.println("1--"+foo);
		System.out.println("2--"+bar);
	}
}
