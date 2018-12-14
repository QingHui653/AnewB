package test.core.javacase.leetcode;

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
        int[] prices = {7,2,6,8,1,5,3,8,4};
        System.out.println("最大利润为"+maxProfit(prices));
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
    public int maxSubArray(int[] nums) {
        return 0;
    }
}
