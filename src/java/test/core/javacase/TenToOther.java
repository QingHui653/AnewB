package test.core.javacase;

import java.util.Scanner;

import org.junit.Test;

public class TenToOther {
	//从十进制转成其他进制时就是不断地除于进制数得到余数，然后把余数反过来串起来就是最后结果
	@Test
	public void unit() {
		tenToOther(8,8);
	}
	
	public static void main(String[] args) {
		System.out.print("请输入一个十进制正整数：");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int i = 0, n1 = n;
		int a[] = new int[100];
		System.out.print("此十进制正整数转化为二进制的数后为：");
		while (n1 != 0) {
			a[i] = n1 % 2;
			n1 = n1 / 2;
			i++;
		}
		for (i = i - 1; i >= 0; i--) {
			System.out.print(a[i]);
		}
		
		
		
		System.out.print("\n此十进制正整数转化为八进制的数后为：");
		i=0;n1=n;
		while (n1 != 0) {
			a[i] = n1 % 8;
			n1 = n1 / 8;
			i++;
		}
		for (i = i - 1; i >= 0; i--) {
			System.out.print(a[i]);
		}
		
		
		System.out.print("\n此十进制正整数转化为十六进制的数后为：");
		i=0;n1=n;
		while (n1 != 0) {
			a[i] = n1 % 16;
			n1 = n1 / 16;
			i++;
		}
		for (i = i - 1; i >= 0; i--) {
			System.out.print(a[i]);
		}
	}
	
	
	public int[] tenToOther(int num, int scale) {
		int i = 0, n1 = num;
		int[] a = new int[100];
		System.out.print("此十进制正整数转化为"+scale+"进制的数后为：");
		while (n1 != 0) {
			a[i] = n1 % 2;
			n1 = n1 / 2;
			i++;
		}
		for (i = i - 1; i >= 0; i--) 
			System.out.print(a[i]);
		return a;
	}
	
	//int[]  转int
}
