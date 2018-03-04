package test.core.javacase.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayTest {

    @Test
    public void arrayTest(){
        int[] arr= {1,1,2,2,3};
        int size =  removeDuplicates(arr);
        System.out.println(size);
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
}
