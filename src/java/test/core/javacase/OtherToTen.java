package test.core.javacase;

import org.junit.Test;

public class OtherToTen {
	
	@Test
	public void unit() {
//		 int num= otherToTen1(357,8);
		 int num= otherToTen2(357,8);
		 System.out.println(num);
	}
	
	//其他进制转8进制 357转8进制
	//3*(8^2)+5*(8^1)+7*(8^0)
	public int otherToTen1(int num, int scale) {
		String number =String.valueOf(num);
		char[] numChar = number.toCharArray();
		int res = 0;
		for (int i = 0,j=numChar.length; i < j; i++) {
			int first =Integer.parseInt(String.valueOf(numChar[i]));
			int last = (int) Math.pow(scale,j-i-1);
			System.out.println("---"+first+"*"+last);
			res+=first*last;
		}
		return res;
	}
	
	//Integer内   其他进制转10进制 357转8进制  239
	//(0+3*8+5)*8+7
	//从左到右遍历字符串的每个字符，然后乘以进制数，再加上下一个字符，接着再乘以进制数，再加上下个字符，不断重复，直到最后一个字符
	public int otherToTen2(int num, int scale) {
		String number =String.valueOf(num);
		char[] numChar = number.toCharArray();
		int res = 0;
		int len = numChar.length;
		int i = 0;
		while(i!=len) {
			int first =Integer.parseInt(String.valueOf(numChar[i]));
			if(i!=len-1) {
				res=(res+first)*scale;
			}else {
				res=(res+first);
			}
			i++;
		}
		return res;
	}
}
