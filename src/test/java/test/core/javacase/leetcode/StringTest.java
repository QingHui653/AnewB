package test.core.javacase.leetcode;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

public class StringTest {

    @Test
    public void stringTest() {
//        System.out.println(reverseString("hello"));
//        System.out.println(reverse(321));
//        System.out.println(firstUniqChar("loveleetcode"));

//        System.out.println(Solution("rat","cat"));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(myAtoi("  0000000000012345678"));
//        System.out.println(strStr("mississippi","issi"));
//        System.out.println(countAndSay(6));
        //["c","acc","ccc"]  {"ca","a"}
        String[] strings = {"flower","owfl","flight"};
        System.out.println(longestCommonPrefix(strings));
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

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * s = "anagram", t = "nagaram" --> true
     * s = "rat", t = "car" -->false
     * 你可以假设字符串只包含小写字母。 如果输入字符串包含 unicode 字符怎么办？
     * @param s
     * @param t
     * @return
     */
    public boolean Solution(String s,String t){
        // 太慢.
        /*Map<Character,Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c= s.charAt(i);
            if(map.get(c)==null){
                map.put(c,1);
            }else {
                map.put(c,(Integer)map.get(c)+1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if(map.get(c)==null){
                return false;
            } else {
                map.put(c,(Integer)map.get(c)-1);
            }
        }

        for (char c : map.keySet()) {
            if (map.get(c)!=0){
                return false;
            }
        }*/
        if(s == null || t == null)
            return false;
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();
        Arrays.sort(schar);
        Arrays.sort(tchar);
        return String.valueOf(schar).equals(String.valueOf(tchar));
    }

    /**
     * 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 本题中，我们将空字符串定义为有效的回文串
     * A man, a plan, a canal: Panama --> true
     * race a car -->false
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s=="") return true;
        s=s.toLowerCase();
        /*LinkedList<Character> fList = new LinkedList<>();
        LinkedList<Character> lList = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if((c>='0'&&c<='9')||(c>='a'&&c<='z')){
                fList.add(s.charAt(i));
                lList.addFirst(s.charAt(i));
            }
        }
        return String.valueOf(fList.toString()).equals(lList.toString());*/
        //两个指针直接位移即可
        int i =0,j=s.length()-1;
        while (i<j){
            while (i<j&&!Character.isDigit(s.charAt(i))&&!(s.charAt(i)>='a'&&s.charAt(i)<='z'))
                i++;
            while (i<j&&!Character.isDigit(s.charAt(j))&&!(s.charAt(j)>='a'&&s.charAt(j)<='z'))
                j--;

            if(s.charAt(i)!=s.charAt(j)) return false;

            i++;j--;
        }
        return true;
    }


    /**
     *  字符串转整数（atoi）
     *  实现 atoi，将字符串转为整数。
     * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
     * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
     * 若函数不能执行有效的转换，返回 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31)
     * 42 ->42  "  -42" ->42 "4193 with words"-> 4193  "words and 987" -> 0  "-91283472332" -> INT_MIN (−2^31)
     * @param str
     * @return
     *
     * 写的看不下去 .
     */
    public int myAtoi(String str) {
        if(str==null || str.isEmpty()) return 0;
        StringBuilder sb = new StringBuilder();
        //排除"   123" 情况
        str=str.trim();
        for (int i = 0; i < str.length(); i++) {
            //排除 "1+" "1-" 情况
            if(sb.length()>0 && (str.charAt(i)=='+'||str.charAt(i)=='-')) break;

            if((str.charAt(i)=='+' || str.charAt(i)=='-') || (str.charAt(i)>='0' && str.charAt(i)<='9')){
                //排除"0-1" "010" "  000000000123456789 情况"
                if(str.charAt(i)=='0'&& sb.length()!=0){
                    if((sb.charAt(0)=='+' || sb.charAt(0)=='-') && sb.length()<2) continue;
                    if(sb.length()>=2 && (sb.charAt(0)=='+' || sb.charAt(0)=='-' || sb.charAt(0)=='0') && sb.charAt(1)=='0') continue;
                }
                sb.append(str.charAt(i));
            //排除 "1 "情况
            }else {
                break;
            }
        }

        //排除 "0" "+" "-" 情况
        if(sb.length()==0 || sb.toString().equals("+") || sb.toString().equals("-")) return 0;
        //排除 "200000000000000000" 情况
        if(sb.length()>11) {
            if(sb.charAt(0)=='-')
                return Integer.MIN_VALUE;
            else
                return Integer.MAX_VALUE;
        }

        Long l = Long.parseLong(sb.toString());

        if(l>=Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(l<=Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return l.intValue();
    }


    /**
     * 实现 strStr() 函数。
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * haystack = "hello", needle = "ll"
     * haystack = "aaaaa", needle = "bba"
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        /*if(needle==null || needle.length()==0) return 0;
        char[] iC = haystack.toCharArray();
        char[] jC = needle.toCharArray();
        for (int i = 0; i < iC.length ; i++) {
            int j = 0; int first = i ;
            while (j<jC.length){
                if(first>=iC.length || j>= jC.length) return -1;
                if(iC[first]==jC[j]){
                    if(j==jC.length-1) return i;
                    first++;
                    j++;
                }else {
                    break;
                }
            }
        }
        return -1;*/
        // 投机分子
        return haystack.indexOf(needle);
    }

    /**
     * 报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     *
     * 给定一个正整数 n ，输出报数序列的第 n 项。
     *
     * 注意：整数顺序将表示为一个字符串。
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: "1"
     * 示例 2:
     *
     * 输入: 4
     * 输出: "1211"
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n <= 0)
            return "";
        StringBuilder res = new StringBuilder();
        StringBuilder pre = new StringBuilder();
        res.append("1");
        for (int i = 1; i < n; i++) {
            pre = res;
            res = new StringBuilder();
            int count = 1;
            for (int j = 1; j < pre.length(); j++) {
                if (pre.charAt(j) == pre.charAt(j - 1)) {
                    count++;
                } else {
                    res.append(count).append(pre.charAt(j - 1));
                    count = 1;
                }
            }
            res.append(count).append(pre.charAt(pre.length() - 1));
        }

        return res.toString();
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * ["flower","flow","flight"]
     * 输出: "fl"
     *
     * ["dog","racecar","car"]
     * 输出: ""
     * @param strs
     * @return
     */

    public String longestCommonPrefix(String[] strs) {
        // MMP 这 找的不是最长 公共前缀.找到 是最长公共子串了
        // 前缀要求 从 0 开始相同
        /*if(strs==null || strs.length ==0 ) return "";
        if(strs.length==1) return strs[0];

        int len = strs.length,preLen=0;
        int[] curr =new int[strs.length];
        StringBuilder res = new StringBuilder("");
        StringBuilder before = new StringBuilder("");
        for (int i = curr[0]; i < strs[0].length(); i++) {

            boolean found =false;
            // 遍历数组
            for (int index = 1; index<len ;index++){
                if(strs[index]==null || strs[index].length()==0) return "";
                boolean noFound = false ;

                //已经 全部有第一位了.只需判断接下来的 位数
                if(preLen!=0){
                    if(curr[index]+preLen < strs[index].length() && strs[0].charAt(curr[0]+preLen)==strs[index].charAt(curr[index]+preLen)){

                    }else {
                        preLen=0;i--;
                        noFound = true;
                    }

                }else {
                    //遍历 数组 字符串
                    for (int j = curr[index]; j < strs[index].length() ; j++) {
                        if(strs[index].charAt(j) == strs[0].charAt(i)){
                            curr[0] = i;
                            curr[index] = j;
                            break;
                        }

                        if(j== strs[index].length()-1) noFound=true;
                    }
                }

                if(noFound){
                    before=res.length()>=before.length()?new StringBuilder(res):new StringBuilder(before);
                    res = new StringBuilder();
                    break;
                }

                if(index==len-1) {
                    found=true;
                    res.append(strs[0].charAt(i));
                }
            }
            if(found)
                preLen++;
        }
        return res.length()>=before.length()?res.toString():before.toString();*/



       int index = 0;
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char current = strs[0].charAt(index);
            for (String str : strs) {
                if (str.length() == i || current != str.charAt(index)) {
                    return str.substring(0, index);
                }
            }
            index++;
        }

        return strs[0].substring(0, index);

    }

}
