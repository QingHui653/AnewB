package test.core.javacase.leetcode;

/**
 * @Auther:b
 * @Date: 2018/12/19
 * @Deseription
 */

import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 */
public class DesignSolution {
    //原始数组
    private int[] nums;
    private Random rand =new Random();
    public DesignSolution(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    // 打乱算法
    public int[] shuffle() {
        if(nums == null) return null;
        int[] a = (int[])nums.clone();
        for(int i = 1; i < nums.length; i++){
            int j = rand.nextInt(i + 1);
            swap(a, i, j);
        }
        return a;
    }

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

