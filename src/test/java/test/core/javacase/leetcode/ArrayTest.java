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

//        int[] nums1 ={1};int[] nums2 ={1,2};
//        int[] nums1 ={};int[] nums2 ={};
//        intersect(nums1,nums2);
        int[] arr3= {9,9,9};
        plusOne(arr3);

        // 定义 nums = [0, 1, 0, 3, 12,13]，调用函数之后， nums 应为 [1, 3, 12,13, 0, 0]
        int[] nums ={-959151711,623836953,209446690,-1950418142,1339915067,-733626417,481171539,-2125997010,-1225423476,1462109565,147434687,-1800073781,-1431212205,-450443973,50097298,753533734,-747189404,-2070885638,0,-1484353894,-340296594,-2133744570,619639811,-1626162038,669689561,0,112220218,502447212,-787793179,0,-726846372,-1611013491,204107194,1605165582,-566891128,2082852116,0,532995238,-1502590712,0,2136989777,-2031153343,371398938,-1907397429,342796391,609166045,-2007448660,-1096076344,-323570318,0,-2082980371,2129956379,-243553361,-1549960929,1502383415,0,-1394618779,694799815,78595689,-1439173023,-1416578800,685225786,-333502212,-1181308536,-380569313,772035354,0,-915266376,663709718,1443496021,-777017729,-883300731,-387828385,1907473488,-725483724,-972961871,-1255712537,383120918,1383877998,1722751914,0,-1156050682,1952527902,-560244497,1304305692,1173974542,-1313227247,-201476579,-298899493,-1828496581,-1724396350,1933643204,1531804925,1728655262,-955565449,0,-69843702,-461760848,268336768,1446130876};
        moveZeroes(nums);
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

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/27/
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len =digits.length;
        // 小于9 直接加一 返回
        // 等于9 为0 高位+1
        // 全为9 ，数组 长度+1，最高位为1 其他为0
        for (int size = len-1; size >= 0; size--) {
            if(digits[size]<9) {
                digits[size]++;
                return digits;
            }
            digits[size]=0;
        }
        int [] array = new int[len+1];
        array[0]=1;
        return array;
    }

    /**
     * https://leetcodechina.com/explore/suan-fa/card/chu-ji-suan-fa/1/di-yi-zhang-jie/28/
     * @param nums
     * 0 1 0 3 12 13
     * 1 0 0 3 12 13
     * 1 3 12 0 0 13
     * 1 3 12 13 0 0
     *
     * 0 1 3 0 12 13
     * 1 0 3 0 12 13
     * 1 3 0 0 12 13
     * 1 3 12 13 0 0
     */
    public void moveZeroes(int[] nums) {
        int newIndex = 0;

        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != 0) {
                nums[newIndex++] = nums[i];
            }
        }
        for (; newIndex < nums.length; newIndex++) {
            nums[newIndex] = 0;
        }

        System.out.println(Arrays.toString(nums));
    }
}
