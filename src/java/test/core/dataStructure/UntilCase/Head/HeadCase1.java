package test.core.dataStructure.UntilCase.Head;

public class HeadCase1 {
	/**
	 * 传入参数为基础数据类型是开辟在栈区，将参数复制一份到方法的栈区，
	 * 不会改变传入的参数的值，改变的只是方法的局部变量
	 * 
	 * 传入为java对象是，将真正的对象放入head堆区
	 * 在栈记录这个对象的地址，因此会改变值
	 */
	public static void main(String args[]) {
		A a = new A(1);
		A b = new A(2);
		swap(a, b);
		//2,1
		System.out.println("a's value is " + a.value + ", b's value is " + b.value);
	}

	public static void swap(A a, A b) {
		int t = a.value;
		a.value = b.value;
		b.value = t;
	}
}

class A {
	public int value;

	public A(int v) {
		this.value = v;
	}
}
