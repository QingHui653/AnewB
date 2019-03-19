package test.core.javacase.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther:b
 * @Date: 2019/3/18
 * @Deseription
 */
public class ArrayTest {


    @Test
    public void test(){
        int [] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists.size());
    }

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     * @param nums
     * @return
     */
    //1. 枚举,n位的 话.判断 循环过多
    //2.大体的思想先将数组排序，从小到大取vector中的数first，再从剩下的数中取和等于 0 - first 的数即可
    //同样的4Sum问题也可以转换成上面的3Sum问题，从而递归的求解，KSum问题也是一样
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            twoSum(ret,nums, i+1, 0 - nums[i]);
            while(i < nums.length - 2 && nums[i] == nums[i+1])
                i++;
        }
        return ret;
    }

    //
    public void twoSum(List<List<Integer>> ret,int[] nums, int start, int value){
        int beg = start;
        int end = nums.length-1;
        while (beg<end){
            if(nums[beg]+nums[end]==value){
                List<Integer> list = new ArrayList<Integer>();
                list.add(nums[start-1]);
                list.add(nums[beg]);
                list.add(nums[end]);
                ret.add(list);
                // 防止重复.
                while(beg < end && nums[beg+1] == nums[beg])
                    beg++;
                while(beg < end && nums[end-1] == nums[end])
                    end--;
                beg++;
                end--;
            }else if(nums[beg] + nums[end] > value){
                    end--;
            }else{
                    beg++;
            }
        }
    }

    public List<List<Integer>> threeSum2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
}
