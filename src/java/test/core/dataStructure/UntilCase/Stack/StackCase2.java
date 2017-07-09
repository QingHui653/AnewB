package test.core.dataStructure.UntilCase.Stack;

public class StackCase2 {
	//将n入栈
	//开辟个fact(n)的栈区，如果有返回值则将值返回到栈顶
	//如果返回值内有表达式，则继续开辟fact(n-1)的栈，相当n的栈*fact(n)的栈*fact(n-1)的栈
	//2*1
	//3*2*1
	//4*3*2*1
	public static void main(String args[]) {
		int n = 4;
		int t = fact(n);
		System.out.println(t);
	}

	public static int fact(int n) {
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}
}
