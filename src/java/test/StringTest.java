package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class StringTest {


	@Test
	public void test() {
		String foo="1";
		String bar=foo;
		foo="2"+1;
		System.out.println("1--"+foo);
		System.out.println("2--"+bar);
		
		int t =1<<4; //位运算  都是二进制 ，在转换  1向右移动4 位 ，变为 10000 转二进制 为16
		
		System.out.println(t);
		
		
		int t2 =2<<4; //2*  1向右平移 4位  2* 16 =32
		
		System.out.println(t2);
				
	}
}
