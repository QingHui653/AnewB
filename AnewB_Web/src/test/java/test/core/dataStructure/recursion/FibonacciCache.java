package test.core.dataStructure.recursion;

public class FibonacciCache {
	
	final int MAX_N=8192;
	
	private int[] _cache=new int[MAX_N];
    
	/**
	 * 使用空间换时间
	 * @param day
	 * @return
	 */
    public int fibonacci(int day){
    	
    	if(_cache[day]!=0){
    		return _cache[day];
    	}
        if (day<2){//F(1)=1
        	_cache[day]=1;
            return 1;
        }else {
        	_cache[day]=fibonacci(day-1)+fibonacci(day-2);
           return _cache[day]; //F(n)=F(n-1)+F(n-2)
        }
    }
    
    /**
     * 真正的尾递归
     * @param a n=0时，返回数据
     * @param b 从第0天开始
     * @param n 天数
     * @return
     */
    public int fib(int a, int b, int n) {
        if (n == 0)
            return a;
        else
            return fib(a + b, a, n - 1);
    }
    
    public static void main(String[] args) {
    	FibonacciCache fibonacciCache =new FibonacciCache();
    	
    	int sum =fibonacciCache.fibonacci(5);
    	
    	System.out.println("sum "+sum);
    	
    	sum=fibonacciCache.fib(1, 0, 5);
    	
    	System.out.println("sum2 "+sum);
	}
}
