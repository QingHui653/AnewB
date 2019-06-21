package test.core.dataStructure.UntilCase.Stack;

public class StackCase1 {
	//首先将1，2入栈
	//调用swap方法需要两个参数 ，又将1，2出栈，复制到swap的栈区
	//修改swap栈区t ,a,b的值
	//然后销毁swap的栈区
	//输出main里面a，b的栈区
	public static void main(String args[]) {
        int a = 1, b = 2;
        swap(a, b); 
        System.out.println("a is " + a + ", b is " + b); //1，2
    }   

    public static void swap(int a, int b){ 
        int t = a;
        a = b;
        b = t;
    } 
}
