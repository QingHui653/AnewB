package test.core.javacase.junior;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther:b
 * @Date: 2018/12/21
 * @Deseription
 */
public class MathSolution {
    /**
     * 写一个程序，输出从 1 到 n 数字的字符串表示。
     * 1. 如果 n 是3的倍数，输出“Fizz”；
     * 2. 如果 n 是5的倍数，输出“Buzz”；
     * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        String s;
        for (int i = 1; i <=n ; i++) {
            s=i+"";
            if(i%3==0 && i%5==0)
                s="FizzBuzz";
            else {
                if(i%3==0) s="Fizz";
                if(i%5==0) s="Buzz";
            }
            list.add(s);
        }
        return list;
    }

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     * 示例:
     * 输入: 10
     * 输出: 4
     * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * @param n
     * @return
     */
    // 超时
    /*public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i))
                count++;
        }
        return count;
    }

   public boolean isPrime(int n) {
       if (n == 1)
           return false;
       for (int i = 2; i * i <= n; i++) {
           if (n % i == 0)
               return false;
       }
       return true;
   }*/
    // 埃拉托斯特尼筛法
    public int countPrimes(int n) {
        int[] mask = new int[n];//能够在这里直接对动态数组进行初始化
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (mask[i] == 0) {
                count++;
                //这里不能将j初始化成i，否则i*j会溢出
                for (int j = 2; i * j < n; j++){
                    mask[i * j] = 1;
                }
            }
        }
        return count;
    }

    /**
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
     * 示例 1:
     * 输入: 27
     * 输出: true
     * 示例 2:
     * 输入: 0
     * 输出: false
     * 示例 3:
     * 输入: 9
     * 输出: true
     * 示例 4:
     * 输入: 45
     * 输出: false
     * 进阶：
     * 你能不使用循环或者递归来完成本题吗？
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        //递归
//        if(n==0) return false;
//        if(n==1) return true;
//        if(n%3==0 && n/3==1) {
//            return true;
//        }else if(n%3!=0) {
//            return false;
//        }
//        return isPowerOfThree(n/3)?true:false;
        //循环
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    //由于输入是int，正数范围是0-2^31，在此范围中允许的最大的3的次方数为319=1162261467，那么我们只要看这个数能否被n整除即可
    public boolean isPowerOfThree2(int n) {
//        return (n > 0 && 1162261467 % n == 0);
        // 只有3^19能整除 那就能一定是 3的 幂次数
        return (n > 0 && Math.pow(3,19) % n == 0);
    }

    /**
     * 最后还有一种巧妙的方法，利用对数的换底公式来做，高中学过的换底公式为logab = logcb / logca，那么如果n是3的倍数，则log3n一定是整数，我们利用换底公式可以写为log3n = log10n / log103，注意这里一定要用10为底数，不能用自然数或者2为底数，否则当n=243时会出错，原因请看这个帖子。现在问题就变成了判断log10n / log103是否为整数，在c++中判断数字a是否为整数，我们可以用 a - int(a) == 0 来判断
     * @param n
     * @return
     */
    public boolean isPowerOfThree3(int n) {
        return (n > 0 && (Math.log10(n) / Math.log10(3)) - Math.log10(n) / Math.log10(3) == 0);
    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * 示例 1:
     * 输入: "III"
     * 输出: 3
     * 示例 2:
     * 输入: "IV"
     * 输出: 4
     * 示例 3:
     * 输入: "IX"
     * 输出: 9
     * 示例 4:
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * @param s
     * @return
     */
    // 先进先出 优先级问题
    public int romanToInt(String s) {
        Map<String,Integer> map = new HashMap();
        map.put("M",1000);
        map.put("D",500);
        map.put("C",100);
        map.put("L",50);
        map.put("X",10);
        map.put("V",5);
        map.put("I",1);
        map.put("CM",900);
        map.put("CD",400);
        map.put("XC",90);
        map.put("XL",40);
        map.put("IX",9);
        map.put("IV",4);

        char[] chars = s.toCharArray();
        Character sumChar='0';
        Character index;
        Character last='0';
        int sum=0;
        for (int i = 0; i <chars.length ; i++) {
            if(last=='0'){
                last=chars[i];
            }else {
                index=last;
                last=chars[i];
                if(map.get(index.toString()+last.toString())!=null){
                    sum+=map.get(index.toString()+last.toString());
                    last='0';
                }else {
                    sum+=map.get(index.toString());
                }
            }
        }
        if(last!='0') return sum+map.get(last.toString());
        return sum;
    }

    @Test
    public void test (){
        //计算能否被 3 5 15 整除
//        System.out.println(Arrays.toString(fizzBuzz(15).toArray()));
        // 计算质数
//        System.out.println(countPrimes(20));
        //3的幂数
//        System.out.println(isPowerOfThree(1));
        // 罗马数字
        System.out.println(romanToInt("MCMXCIV"));
    }
}
