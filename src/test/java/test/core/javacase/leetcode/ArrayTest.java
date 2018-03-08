package test.core.javacase.leetcode;

import org.junit.Test;

import java.util.*;

public class ArrayTest {

    @Test
    public void arrayTest(){
        int[] arr= {1,0,1};
//        System.out.println(removeDuplicates(arr));
//        System.out.println(maxProfit(arr));
        int[] arr2= {1,2,3,4,5,6};
//        rotateByArray(arr2,1);
//        rotateByMap(arr2,1);
//        rotate(arr2,2);
//        rotate(2,arr2);
//        System.out.println(containsDuplicate(arr));
//        System.out.println(singleNumberBySort(arr));
//        System.out.println(singleNumber(arr));

        int[] nums1 ={1};int[] nums2 ={1,2};
//        int[] nums1 ={};int[] nums2 ={};
        intersect(nums1,nums2);

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

    /**
     * 计算下个 位置 存储 下个 位置的 值，
     * 设置 下个 位置 为当前位置 值
     * 下个位置 值 赋值 给 当前 位置 值
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (nums==null || (k %= nums.length) == 0) return;

        int len =nums.length, i=0;
        int start =0, startValue= nums[start];
        int pos = 0;

        while (i++<len){
            pos = (pos+k)%len;
            int t =nums[pos];
            nums[pos] = startValue;

            startValue = t;
            //能 整除 会 发生 循环
            // 如果 发生 循环 时，重新 定位
            if(pos==start){
                start++; pos++;
                startValue=nums[start];
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public void rotate( int k, int[] nums) {
        if (nums==null || (k %= nums.length) == 0) return;
        int len = nums.length, i = 0;
        int pos = 0;
        int start = 0,startValue = nums[pos] ;
        while (i++ < len) {
            pos = (pos + k) % len;
            int t = nums[pos];
            nums[pos] = startValue;
            if (pos == start) {
                ++start; ++pos;
                startValue = nums[pos];
            } else {
                startValue = t;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/24/
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
//        boolean isRepeat = false;
        // 使用 for 循环 会 超出 时间 限制
        // 使用 空间换取 时间
        /*for (int i = 0,len = nums.length; i < len; i++) {
            for (int j = i+1; i+1< len ; j++) {
                if(nums[i]==nums[j]) return true;
            }
        }
        return false;*/

        Set set = new HashSet();
        for (int i = 0,len = nums.length; i < len; i++) {
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }

        return false;
    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/25/
     * @param nums
     * @return
     */
    //调用 java 自带 排序 然后 两两相邻 比较
    //1与2 比 3与4比 比完 偶数会出来，奇数 取剩余
    public int singleNumberBySort(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        int size = 0;
        for (int i = 0,len = nums.length ; i < len ; i++) {
            if(i+1>=len||nums[i]!=nums[i+1]){
                size = nums[i];
                break;
            }
            i++;
        }
        return size;
    }
    //XOR运算 异或运算
    //相同 数字 异或为0
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0,len = nums.length ; i < len ; i++) {
            result ^=nums[i];
        }
        return result;
    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/26/
     * 判断 nums1 对于 nums2 是否 交集
     * {1,1} {1} -> {1}
     * {1}  {1,1} ->{1,1}
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] nums3 ={};
        if(nums1.length==0||nums2.length==0) return nums3;

        Map map = new HashMap();
        for (int i = 0; i < nums2.length ; i++) {
            if(map.get(nums2[i])==null){
                map.put(nums2[i],1);
            }else {
                map.put(nums2[i],(int)map.get(nums2[i])+1);
            }
        }

        List numsList = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if(map.get(nums1[i])!=null&&(int)map.get(nums1[i])>=1){
                numsList.add(nums1[i]);
                map.put(nums1[i],(int)map.get(nums1[i])-1);
            }
        }

        nums3 = new int[numsList.size()];
        for (int i = 0; i < numsList.size() ; i++) {
            nums3[i]=(int)numsList.get(i);
        }

        System.out.println(Arrays.toString(nums3));
        return nums3;
    }
}
