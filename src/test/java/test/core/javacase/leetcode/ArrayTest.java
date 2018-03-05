package test.core.javacase.leetcode;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayTest {

    @Test
    public void arrayTest(){
        int[] arr= {1,1,2,2,3};
//        System.out.println(removeDuplicates(arr));
//        System.out.println(maxProfit(arr));
        int[] arr2= {1,2,3,4,5,6};
//        rotateByArray(arr2,1);
//        rotateByMap(arr2,1);
//        rotate(arr2,2);

    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/21/
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums){

        if(nums==null|| nums.length==0) return 0;

        //从 0 开始 比较
        int size = 0 ;
        for (int i = 0, j = nums.length; i+1<j;i++){

            // 因为 是从 小的 比较 大的 ，所以需要 先 size++，在替换
            if(nums[i]!=nums[i+1]){
                size++;
                nums[size] =nums[i+1];
            }
        }
        System.out.println(Arrays.toString(nums));
        return size+1;
    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/22/
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices){
        int profit = 0;

        for (int i = 0,j=prices.length; i+1 < j; i++) {
            if(prices[i]<prices[i+1]){
                profit+= prices[i+1]-prices[i];
            }
        }
        return profit;
    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/23/
     * @param nums
     * @param k 右旋转 的 位数
     */
    //借助 数组
    public void rotateByArray(int[] nums, int k) {

        int[] newNums=new int[nums.length];
        for (int i = 0,j=nums.length; i < j; i++) {
            newNums[(i+k)%nums.length] =nums[i];
        }
        nums=newNums;

        System.out.println(Arrays.toString(newNums));
        System.out.println(Arrays.toString(nums));
    }

    //借助 Map
    public void rotateByMap(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        k = k % nums.length;
        for (int i = 0,j=nums.length; i < j; i++) {
            int pos =i+k>=j?i+k-j:i+k;
            map.put(pos,nums[pos]);
            if(map.get(i)!=null){
                nums[pos]= map.get(i);
            }else {
                nums[pos]= nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
    }

}
