package test.core.javacase.junior;

import org.junit.Test;

/**
 * @Auther:b
 * @Date: 2018/12/14
 * @Deseription
 */
//动态规划
public class DynamicPlanning {

    @Test
    public void dynamicPlanningTest(){
        // 爬楼梯
//        int step =4;
//        System.out.println("爬"+step+"阶 总共需 "+climbStairs(step));
        //买卖股票
//        int[] prices = {7,2,6,8,1,5,3,8,4};
//        System.out.println("最大利润为"+maxProfit(prices));
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        System.out.println("最大和的连续子数组"+ maxSubArray2(nums));
        int[] nums = {2,1,1,2};
        System.out.println(rob(nums));
    }
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * 示例 1：
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     *
     * 示例 2：
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */
    // 1 1 2 3 5
    // 爬 i 阶 步数 为 i-1 + i-2 的 步数,斐波拉契数列
    public int climbStairs(int n) {
        int fbn1 = 0,fbn2=1;
        for (int i = 1; i <=n ; i++) {
            int tmp = fbn1+fbn2;
            fbn1=fbn2;
            fbn2=tmp;
        }
        return fbn2;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * 注意你不能在买入股票前卖出股票。
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * @param prices
     * @return
     */
    //一次循环即可,存储 min,然后计算利润,取利润最大
    public int maxProfit(int[] prices) {
        if(prices.length<2)  return 0;
        int min = prices[0];
        int profit = 0;
        for(int i = 1; i < prices.length; ++i){
            if (prices[i] < min) {
                min = prices[i];
                continue;
            }
            if ((prices[i] - min) > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
//        if(nums.length==1) return nums[0];
//        if(nums.length==2){
//            if(nums[0]<0 || nums[1]<0)
//                return nums[0]>nums[1]?nums[0]:nums[1];
//            return nums[0]+nums[1];
//        }
//        int max= nums[0]+nums[1];
//        int add= nums[0]+nums[1];
//        for (int i = 1,j=2; i <= nums.length-2 ; i++,j++) {
////            System.out.println("add "+i+" "+j +" "+(nums[i]+nums[j]));
//            if(nums[j]>0){
//                add+=nums[j];
//                if(i==nums.length-2 && add>max)
//                    max=add;
//            }else {
//                if(max<add){
//                    max=add;
//                }
//                add= nums[i]+nums[j];
//            }
//        }
//        return max;
        int curMax = nums[0];
        int maxSum = nums[0];
        for(int i = 1;i<nums.length;++i){
            curMax = Math.max(curMax+nums[i],nums[i]);
            if(curMax > maxSum)
                maxSum = curMax;
        }

        return maxSum;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int sum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max)
                max = sum;
            if (sum <= 0) {
                sum = 0;
            }
        }
        return max;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2:
     * 输入: [3,7,9,3,1,5]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * @param nums
     * @return
     */
    // 分两张情况 奇数,直接 相邻判断即可
    // 偶数还需要判断1,4这种情况
    // 2,1,1,2,,2 ->4
    //https://www.cnblogs.com/grandyang/p/4383632.html
    public int rob(int[] nums) {
        int num1=0,num2=0;
        for (int i = 0; i <nums.length ; i++) {
            if( i % 2==0) num1=Math.max(num1+nums[i],num2);
            else num2=Math.max(num1,num2+nums[i]);
        }
        return num1>num2?num1:num2;
    }
}
