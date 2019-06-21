package test.core.javacase.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:b
 * @Date: 2019/5/29
 * @Deseription
 */
public class easy {

    @Test
    public void easyTest(){
//        romanToInt("III");
        String[] a= {"dog","racecar","car"};
        longestCommonPrefix(a);
    }
    //1. 两数 之和.
    //1. 暴力法 2. 存到哈希表在查 3. 一边存哈希表一边查
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target -nums[i];
            if(map.containsKey(complement))
                return new int[]{map.get(complement),i};
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("未找到.");
    }

    //7.整数反转
    //-2~31 2~31-1 ,最大负数反转 溢出.
    // %10 获取最后一位.
    public int reverse(int x) {
        int y = 0;
        int n;
        while (x!=0){
            n=x%10;
            if(y> Integer.MAX_VALUE/10 || y< Integer.MIN_VALUE/10) return 0;
            y=y*10+n;
            x=x/10;
        }
        return y;
    }

    //9. 回文数
    // 判断边界, /1000 获取最高位,%10获取最低位
    //1221
    public boolean isPalindrome(int x) {
        if(x<0 || (x%10==0 && x!=0)) return false;
        int div =1;
        while (x/div>=10) div*=10;
        while (x>0){
            int left = x/div;
            int right = x%10;
            if(left!=right) return false;
            //x%div 取后面几位 几个0就取几位,/10不要最低位
            x=(x%div)/10;
            div/=100;
        }
        return true;
    }

    //13.罗马数字转整型.
    //MathSolution.class 中已有, 自己写的,运行太慢.
    //主要是通过遍历
    public int romanToInt(String s) {
        Map<String,Integer> map = new HashMap();
        map.put("M",1000);
        map.put("D",500);
        map.put("C",100);
        map.put("L",50);
        map.put("X",10);
        map.put("V",5);
        map.put("I",1);
//        map.put("CM",900);
//        map.put("CD",400);
//        map.put("XC",90);
//        map.put("XL",40);
//        map.put("IX",9);
//        map.put("IV",4);
        char[] chars = s.toCharArray();
        int sum=0;
        //只要小的 在 大的 右边,则一定是减,否则加
        //最后一位单独处理
        for (int i = 0; i <chars.length-1 ; i++) {
            if(map.get(String.valueOf(chars[i]))>=map.get(String.valueOf(chars[i+1])))
                sum+=map.get(String.valueOf(chars[i]));
            else
                sum-=map.get(String.valueOf(chars[i]));
        }
        sum+=map.get(String.valueOf(chars[chars.length-1]));
        return sum;
    }

    //14.最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        for (int i = 0; i < strs[0].length() ; i++) {
            int current=strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                // 只要不相等 .或者比完了,就返回
                if (str.length() == i || current != str.charAt(i))
                    return str.substring(0, i);
            }
        }
        return  strs[0];
    }
}
