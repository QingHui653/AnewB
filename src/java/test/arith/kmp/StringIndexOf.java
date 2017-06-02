package test.arith.kmp;

public class StringIndexOf {
	
	public static void main(String[] args) {
		String a="ababaababac";
		System.out.println(a.length());
		String b="ababac";
		int c= indexOf(a, b);
		System.out.println(c);
	}
	private static int indexOf(String a, String b) {  
		int time =1;
        //1.定义两个指针，分别指向a和b  
        int i = 0;  
        int j = 0;  
        //2.遍历  
        while(i<a.length()&&j<b.length()){
        	System.out.println(time+ "  "+a.charAt(i)+"  "+b.charAt(j));
            if(a.charAt(i)==b.charAt(j)){  
                i++;  
                j++;  
            }  
            else{  
                i = i-(j-1);  
                j = 0;  
            }
            time++;
        }  
        /* 
         * 匹配 
         * 1.匹配a的中间字符串i<a.length() 
         * 2.匹配a的最后字符串(i==a.length()&&j==b.length()) 
         * */  
        if((i==a.length()&&j==b.length())||i<a.length()){  
            return i-b.length();  
        }  
        else{  
            return -1;  
        }  
    }
}
