package test.core.arith.kmp;

public class KMP {
	
	public static void main(String[] args) {
		String a="ababaab";
		System.out.println(a.length());
		String b="ababac";
		int c= KMP_indexOf(a, b);
		System.out.println(c);
	}
	
	/** 
     * O（m+n）  平摊分析 
     *   
     * @param a 表示文本字符串 
     * @param b 表示模式字符串 
     * @return 
     */  
    public static int KMP_indexOf(String a,String b){  
    	int time=1;
        int n = a.length();   
        int m = b.length();  
        //变成字符数组的原因是因为我们需要从索引1开始记录数据，比如a = "aba"，则cha = {' ','a','b','a'};  
        char cha[] = (" "+a).toCharArray();       
        char chb[] = (" "+b).toCharArray(); 
//        char cha[] =a.toCharArray();       
//        char chb[] =b.toCharArray();  
        int j = 0;  //b的指针  
        int[] p = computePArray(chb);       //根据b字符串预先计算出p数组  
        for(int i=1;i<=n;i++){
        	System.out.println(time+ "  "+chb[j+1]+"  "+cha[i]);
            while(j>0 && chb[j+1]!=cha[i]){  //j值最多只能减少m次，通过把m次摊还给n次for循环          
                j = p[j];               //回朔  
            }  
            if(chb[j+1]==cha[i]){         
                j++;  
            }  
            if(j==m){           //j已经匹配到了尾端，所以全部匹配  
                return i-m;  
            }
            time++;
        }  
        return -1;  
    }  
  
    private static int[] computePArray(char[] chb) {  
        int[]p = new int[chb.length+1];  
        p[1] = 0;  
        int j = 0;  
        for(int i=2;i<chb.length;i++){
        	System.out.println("--"+chb[j+1]+"--"+chb[i]);
            while(j>0&&chb[j+1]!=chb[i]){  
                j = p[j];  
            }  
            if(chb[j+1]==chb[i]){  
                j++;  
            }  
            p[i] = j;  
        }  
        return p;  
          
    }
    
    public static void computePArray(String T,int p[]){  
        T = " "+T;  
        int j=0;  
        p[1] = 0;  
        for(int i=2;i<p.length;i++){  
            while(j>0&&T.charAt(j+1)!=T.charAt(i)){  
                j = p[j];  
            }  
            if(T.charAt(j+1)==T.charAt(i)){  
                j++;  
            }  
            p[i] = j;  
        }  
    }  
}
