package test.core.javacase.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther:woshizbh
 * @Date: 2018/11/26
 * @Deseription
 */
public class SortAndSearchTest {

    @Test
    public void test(){
        int[] nums1 = {2,0};
        int[] nums2 = {1};
//        merge(nums1,1,nums2,1);
        System.out.println(firstBadVersion(5));
    }

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * 说明:
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i =0 ,j = 0 ,index=0;
        int[] num1  = new int[nums1.length];
        while (j<n && i< m){
            if(nums1[i]<=nums2[j]){
                num1 [index++]=nums1[i++];
            }else {
                num1 [index++]=nums2[j++];
            }
        }

        while(i < m) num1[index++] = nums1[i++];
        while(j < n) num1[index++] = nums2[j++];

//        nums1 = num1; 此方法无法通过 leetcode 校验
        System.arraycopy(num1, 0, nums1, 0, m + n);
        System.out.println(Arrays.toString(nums1));
    }

    // 末尾填充法.从后往前 填充
//    先当两个数组都有元素的时候填充大的到末尾，如果有一个数组的数用完了，说明剩下的那个数组的所有数都小于当前填充的位置：
//    如果是第一个数组用完了，说明剩下的(最小的那些)全是数组2，把数组2填充进去就好了
//    如果是第二个数组用完了，说明剩下的全是数组1，不用填充了，他们已经在了
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, writeIdx = m + n - 1;
        while (i >= 0 && j >= 0)
            nums1[writeIdx--] = nums1[i] > nums2[j]? nums1[i--] : nums2[j--];
        while (j >= 0)
            nums1[writeIdx--] = nums2[j--];
    }

    //3. 直接将2 加入1 然后使用 Array.sort...


    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 示例:
     *
     * 给定 n = 5，并且 version = 4 是第一个错误的版本。
     *
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     *
     * 所以，4 是第一个错误的版本。
     * @param n
     * @return
     */
    // 二分法checkSelect
    public int firstBadVersion(int n) {
        int min = 1, max = n, mid = 0;
        while(min <= max){
            mid = min + (max - min) / 2;
            if(isBadVersion(mid)){
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    // firstBadVersion 的 条件
    public Boolean isBadVersion(int version){
        switch (version){
            case 1: return false;
            case 2: return false;
            case 3: return false;
            case 4: return true;
            case 5: return true;
        }
        return true;
    }
}
