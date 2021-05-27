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

//        String[] a= {"dog","racecar","car"};
//        longestCommonPrefix(a);

        hammingDistance(1,4);
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

    /**
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     *
     * 注意：
     * 0 ≤ x, y < 231.
     *
     * 示例:
     *
     * 输入: x = 1, y = 4
     *
     * 输出: 2
     *
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     *
     * 上面的箭头指出了对应二进制位不同的位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/hamming-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param x
     * @param y
     * @return
     * 官方//https://leetcode-cn.com/problems/hamming-distance/solution/yi-ming-ju-chi-by-leetcode-solution-u1w7/
     * 思考//1. 与还是非 比较不为0的 --(异或^ 两个位不同为 1)
     * 概念//汉明距离（英语：Hamming distance）是两个字符串对应位置的不同字符的个数。换句话说，它就是将一个字符串变换成另外一个字符串所需要替换的字符个数
     * 位操作//https://blog.csdn.net/weixin_34284188/article/details/88016444
     * >> 右移 >>>无符号右移 << <<< 同理
     * ^ 异或 两个操作数对应的二进制位，相同为0，不同为1。如：0^0=0； 0^1=1； 1^0=1； 1^1=0
     * & 与 两个操作数对应的二进制位，都为1则为1，否则为0。如：1&1=1； 1&0=0； 0&1=0； 0&0=0
     * | 或 两个操作数对应的二进制位，有1则为1，否则为0。如：1|1=1； 1|0=1； 0|1=1； 0|0=0
     * ~ 非 两个操作数对应的二进制位，取反操作。如：1->0； 0->1
     */
    public int hammingDistance(int x, int y) {
        //1. 第一种 异或后,使用内部api
//        return Integer.bitCount(x ^ y);
        //2. 异或后,右移比较
        //我们可以不断地检查 ss 的最低位，如果最低位为 1，那么令计数器加一，然后我们令 ss 整体右移一位，这样 ss 的最低位将被舍去，原本的次低位就变成了新的最低位。我们重复这个过程直到 s=0s=0 为止。这样计数器中就累计了 ss 的二进制表示中 11 的数量
//        int s = x ^ y, ret = 0;
//        while (s != 0) {
//            ret += s & 1;
//            s >>= 1;
//        }
//        return ret;
        //3. 异或后, 看网站
        //该算法可以被描述为这样一个结论：记 f(x)f(x) 表示 xx 和 x-1x−1 进行与运算所得的结果（即 f(x)=x~\&~(x-1)f(x)=x & (x−1)），那么 f(x)f(x) 恰为 xx 删去其二进制表示中最右侧的 11 的结果。
        //恰为 x 删去其二进制表示中最右侧的 1 的结果。跳过了中间 的0不遍历.
        int s = x ^ y, ret = 0;
        while (s!=0) {
            s &= (s - 1);
            ret++;
        }
        return ret;
    }
}
