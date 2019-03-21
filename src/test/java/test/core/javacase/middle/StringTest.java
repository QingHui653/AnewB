package test.core.javacase.middle;

import org.junit.Test;

import java.util.*;

/**
 * @Auther:b
 * @Date: 2019/3/21
 * @Deseription
 */
public class StringTest {

    @Test
    public void test(){

        String s = "dvdf";
        lengthOfLongestSubstring2(s);
    }
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * @param s
     * @return
     */

    public int lengthOfLongestSubstring(String s) {
        // dvdf
//        if(s.length()==0) return 0;
//        if(s.length()==1) return 1;
//        char[] chars= s.toCharArray();
//        String stb= new String();
//        Integer leng = 0;Integer maxLeng=0;
//        for (char aChar : chars) {
//            if(stb.contains(String.valueOf(aChar))){
//                stb=new String();
//                if(leng>maxLeng) maxLeng =leng;
//                leng=0;
//            }
//            stb+=aChar;
//            leng++;
//        }
//        System.out.println(maxLeng);
//        return maxLeng>leng?maxLeng:leng;

        // 傻叉做法 ,遍历 n 次时间长到没法直视
        //首先可以很显然地得到O(n^3)时间复杂度的算法。在提交时会报运行超时的错误。
        if(s.length()==0) return 0;
        if(s.length()==1) return 1;
        char[] chars= s.toCharArray();
        Integer start=0,end=0,length=0;
        for ( ; start < chars.length; start++) {
            end=start+1;
            if(end<chars.length){
                String str = String.valueOf(chars[start]);
                for ( ; end < chars.length ; end++) {
                    if(str.contains(String.valueOf(chars[end])))
                        break;
                    str+=String.valueOf(chars[end]);
                }
                if(end-start>length)
                    length=end-start;
            }
        }
        System.out.println(length);
        return  length;
    }

    //  当遇到重复时,直接从 重复数的 下一位开始 (如: dvdf,在第三位d重复,直接从开始)
    // 两个指针移动.  pwwkew, 先动尾指针,在 动头指针.
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                // 先set.add进去,在 j++;
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                // 移除 重复的. 在 i,即 start所在位置,在++;
                set.remove(s.charAt(i++));
            }
        }
        return ans;
        /*
            int n = s.length(), ans = 0;
            int[] index = new int[128]; // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                i = Math.max(index[s.charAt(j)], i);
                ans = Math.max(ans, j - i + 1);
                index[s.charAt(j)] = j + 1;
            }
            return ans;
         */
    }
}
