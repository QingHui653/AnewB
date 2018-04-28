package test.core.javacase.leetcode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StringTest {

    @Test
    public void stringTest() {
//        System.out.println(reverseString("hello"));
//        System.out.println(reverse(321));
        System.out.println(firstUniqChar("loveleetcode"));


    }

    /**
     * 反转 字符串
     * @param s
     * @return
     */
    public String reverseString(String s) {
//        return new StringBuilder(s).reverse().toString();
        if(s==null) return null;
        int head=0,end=s.length()-1;
        char[] str = s.toCharArray();

        while (head<end){
            char tmp=str[head];
            str[head]=str[end];
            str[end]= tmp;
            head++;
            end--;
        }
        return String.valueOf(str);
    }

    /**
     * 反转整数 有符号32位
     * 其数值范围是 [−23^1,23^1 − 1] 溢出返回0
     * 123->321 -123->-321 210->12
     * @param x
     * @return
     */
    public int reverse(int x) {
        int y = 0; //存放结果
        int n; //余数
        while (x!=0){
            n = x%10;
            if(y>Integer.MAX_VALUE/10 || y<Integer.MIN_VALUE/10) return 0;
            y=y*10+n;
            x/=10;
        }
        return y;
    }

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    public int firstUniqChar(String s) {
//        if(s==null) return -1;
//        Map<Character,Integer> map = new HashMap();
//        int i=0;
//        char[] str =s.toCharArray();
//        while (i<str.length){
//            if(map.get(str[i])==null){
//                map.put(str[i],1);
//            }else {
//                map.put(str[i],map.get(str[i])+1);
//            }
//            i++;
//        }
//        //遍历map 找出最小的
//        for (int j = 0; j < str.length; j++) {
//            if(map.get(str[j])==1) return j;
//        }
//
//        return -1;
        int start,end,result = s.length();
        for(char ch ='a';ch<= 'z';ch++){
            start=s.indexOf(ch);
            end=s.lastIndexOf(ch);
            if(start==end && start!=-1)
                result = Math.min(result,start);
        }

        if(result == s.length()) return -1;

        return result;
    }
}
