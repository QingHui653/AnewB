package test.core.javacase.middle;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Middle {

    @Test
    public void middleTest(){

        int res ;
        int[] candy ={5,2,6,4,1};
        int[][] quest ={{3,1,2},{4,10,3},{3,10,100},{4,100,30},{1,3,1}};
        canEat(candy,quest);

        int[] array= {1,1,0,1,1,0};
        res = findMaxLength(array);

        System.out.println(res);
    }

    /**
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     * https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/
     * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
     *
     * 你按照如下规则进行一场游戏：
     *
     * 你从第 0 天开始吃糖果。
     * 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
     * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
     * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；
     * 否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
     *
     * 请你返回得到的数组 answer 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
     * 输出：[true,false,true]
     * 提示：
     * 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
     * 2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
     * 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
     * 示例 2：
     *
     * 输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
     * 输出：[false,true,true,false,false]
     *  
     *
     * 提示：
     *
     * 1 <= candiesCount.length <= 105
     * 1 <= candiesCount[i] <= 105
     * 1 <= queries.length <= 105
     * 0 <= favoriteDayi <= 109
     * 1 <= dailyCapi <= 10
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candiesCount
     * queries[i].length == 3
     * 0 <= favoriteTypei < candiesCount.length
     * @param queries
     * @return
     * 存在 candiesCount.length 种类的糖,总共 candiesCount数组和怎么多,
     * 求问题 {x,y,z}在每天最多吃z个的情况下,在 x 天吃到 y类. 且吃糖只能先从种类1开始吃(必须吃一个)
     * 1.第一种情况最简单.即使每天吃z个,在 x天也吃不到y类(x*z<(1-(y-1)类的糖果总和))
     * 2.每天吃1个 也x天也超过y类 x*1>(1-y的糖果总和)
     * 3.
     * --
     * 根据题意，第 i 天可吃到的糖果数量为 [days + 1, (days + 1) * dailyCap]，而由于糖果必须按类依次消耗，第 j 种糖果在被吃序列中的索引为 (candiesSum[j - 1], candiesSum[j]]，因此只需判断 2 个区间是否存在交集，即可得到当前答案
     * 吃糖果数量的边界为 第 i 天可吃到的糖果数量为 [days + 1, (days + 1) * z]
     * 第 y 种糖果在被吃序列中的索引为 (candiesSum[y - 1], candiesSum[y]]
     *
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length, candiesType=candiesCount.length;
        long[] candiesSum = new long[candiesType];
        //使用一个 数组 来存放 每一个type sum和
        candiesSum[0] = candiesCount[0];
        for (int i = 1; i < candiesType; i++) {
            candiesSum[i] = candiesSum[i-1] +candiesCount[i];
        }

        boolean[] booleans = new boolean[len];

        for (int i = 0; i < len; i++) {
            int type = queries[i][0],day = queries[i][1], dayLimit = queries[i][2];
            long min = type>0?candiesSum[type-1]:0;
            long max = candiesSum[type];
            if(day+1<=max && (long) (day + 1) *dayLimit>min){
                booleans[i] = true;
            }else {
                booleans[i] = false;
            }
        }

        return booleans;
    }

    /**
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     *  
     * 示例 1:
     * 输入: nums = [0,1]
     * 输出: 2
     * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
     * 示例 2:
     *
     * 输入: nums = [0,1,0]
     * 输出: 2
     * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * nums[i] 不是 0 就是 1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/contiguous-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     * nums 为二进制数组 只存在 0,1
     * 1.首先相同数量则一定为偶数
     * 2.设立两个指针.(1个为遇到0-1,遇到1+1,当sum=0时,第二个指针指向此处)
     * --
     * 如果答案非 0，那么子数组长度必然为偶数，且长度至少为 2的两倍
     * 前缀和 + 哈希表
     * 具体的，我们在预处理前缀和时，将 nums[i] 为0 的值当做 -1−处理。
     *
     * 从而将问题转化为：如何求得最长一段区间和为 0 的子数组。
     *
     * 同时使用「哈希表」来记录「某个前缀和出现的最小下标」是多少。
     *
     * 再结合「如果答案非 00，子数组长度至少为 22」的特性，我们让循环从 22 开始。
     *
     * PS. 哈希表常数还是比较大的，用数组模拟哈希表的卡常代码见 P2。
     *
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/contiguous-array/solution/gong-shui-san-xie-qian-zhui-he-ha-xi-bia-q400/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        //求总和,0为 -1.
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //如果存在则必须为2开始
        for (int i = 2; i <= n; i++) {
            if (!map.containsKey(sum[i - 2])) map.put(sum[i - 2], i - 2);
            if (map.containsKey(sum[i])) ans = Math.max(ans, i - map.get(sum[i]));
        }
        return ans;
    }

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     *
     * 输入：nums = [1], target = 1
     * 输出：1
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 1.感觉是个界限问题.
     */

    public int findTargetSumWays(int[] nums, int target) {

        return 0;
    }
}
