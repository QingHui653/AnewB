package test.core.arith;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int k =5;
		int[][] g=new int[k][];
		Test t= new Test();
		 g=t.run(k);
		 System.out.println(Arrays.deepToString(g));
		 String[] c=Arrays.deepToString(g).split("],");
		 for (String string : c) {
			System.out.println(string);
		}
	}
	
	
	public int[][] run(int k) {
		int i=0,j=0,f=1,h=k;
		int[][] g=new int[k][k];
		while(k>1){
			if(j==0){
				while (i<k) {
					g[i][j]=f;
					f++;
					i++;
				}
			} 
			if (i==k) {
				while (j<k-1) {
					j++;
					g[i-1][j]=f;
					f++;
				}
			} 
			if (j==k-1) {
				while (i>h-k) {
					i--;
					if(i>=1){
						g[i-1][j]=f;
						f++;
					}
				}
			} 
			if (i==0) { //
				while (j>h-k) {
					j--;
					if(j>=1){
						g[i][j]=f;
						f++;
					}
				}
			}
			k--;
		}
		return g;
		}
	}
