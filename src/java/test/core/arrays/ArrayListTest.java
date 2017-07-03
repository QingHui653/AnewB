package test.core.arrays;

import java.util.Arrays;

public class ArrayListTest<T> {
	
	private T t;
	
	Object[] data;
	
	int size;
	
	private static final Object[] DEF = {};
	
	public static void main(String[] args) {
//		ArrayList<String> l=new ArrayList<String>();
//		int[] i= new int[3];
		ArrayListTest<String> n =new ArrayListTest<String>();
		n.add("2");
		System.out.println("测试"+Arrays.toString(n.data));
	}
	
	
	
	public ArrayListTest() {
		this.data=DEF;
	}

	public ArrayListTest(int i) {
		if (i>0) {
			this.data=new Object[i];
		}else if(i==0){
			this.data=DEF;
		}else {
			System.out.println("输出错误:请输入>0的数字");
		}
	}

	public Boolean add(T t) {
		Object[] o =new Object[data.length+1];
		System.arraycopy(data, 0, o, 0, data.length);
		System.out.println("data.length--"+data.length);
		System.out.println("o.length--"+o.length);
		o[data.length]=t;
		this.data=o;
		return true;
	}
}
