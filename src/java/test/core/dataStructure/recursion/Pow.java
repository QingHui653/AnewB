package test.core.dataStructure.recursion;

public class Pow {
	/* m的n次方  O(n)*/
	public double pow(double m, int n){
		double result= 1;
		for (int i = 0; i < n; i++) {
			result*=m;
		}
		return result;
	};
	/*递归版  O(lg n)*/
	public double power(double m, int n) {
	    if (n == 0)
	        return 1;
	    else if ((n & 1) == 1)
	        return m * power(m, n - 1); 
	    else {
	        double t = power(m, n / 2); 
	        return t * t;
	    }   
	}
	
	public static void main(String[] args) {
		Pow pow =new Pow();
		double n = pow.pow(2, 3);
		System.out.println(n);
		double n2 = pow.power(2, 3);
		
		System.out.println(n2);
	}
}
