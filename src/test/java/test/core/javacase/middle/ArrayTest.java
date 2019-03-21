package test.core.javacase.middle;

import org.junit.Test;

import java.util.*;

/**
 * @Auther:b
 * @Date: 2019/3/18
 * @Deseription
 */
public class ArrayTest {


    @Test
    public void test(){
//        int [] nums = {-1, 0, 1, 2, -1, -4};
//        List<List<Integer>> lists = threeSum(nums);
//        System.out.println(lists.size());

//        int[][] nums2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
//        setZeroes(nums2);

//        String[] a=  {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] a=  {"", "",};
        groupAnagrams2(a);
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

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     * 示例 1:
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2:
     *
     * 输入:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     * 进阶:
     *
     * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
     * @param matrix
     */
    //1.直接新建一个和matrix等大小的矩阵，然后一行一行的扫，只要有0，就将新建的矩阵的对应行全赋0，行扫完再扫列，然后把更新完的矩阵赋给matrix即可
    //2.找出 0的 坐标, 将他的 纵坐标和 横坐标,存起来 .重新 重置 横坐标和 纵坐标;
    //3. 不新建整数 ,在 原数组 操作,如果存在0,就将 对应的 第一行,第一列的 值设为0 ,最后遍历 第一行第一列就行.

    // 这道题的要求是用O(1)的空间，那么我们就不能新建数组，我们考虑就用原数组的第一行第一列来记录各行各列是否有0.
    //- 先扫描第一行第一列，如果有0，则将各自的flag设置为true
    //- 然后扫描除去第一行第一列的整个数组，如果有0，则将对应的第一行和第一列的数字赋0
    //- 再次遍历除去第一行第一列的整个数组，如果对应的第一行和第一列的数字有一个为0，则将当前值赋0
    //- 最后根据第一行第一列的flag来更新第一行第一列
    public void setZeroes(int[][] matrix) {
        List<Integer> yIndex= new ArrayList();
        List<Integer> xIndex= new ArrayList();
        for (int y = 0; y < matrix.length; y++) {
            int[] yArys = matrix[y];
            for (int x = 0; x < yArys.length ; x++) {
                if(yArys[x]==0){
                    yIndex.add(y);
                    xIndex.add(x);
                }
            }
        }
        // 重置 x轴
        for (Integer index : xIndex) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][index]=0;
            }
        }
        // 重置 y轴
        for (Integer index : yIndex) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[index][i]=0;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * 说明：
     *
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     * @param strs
     * @return
     */
    // 枚举
    // 未能通过
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList();

        for (String str : strs) {
            if(list.size()==0){
                List<String> array  = new ArrayList();
                array.add(str);
                list.add(array);
            } else {
                // 全部在 list 中是否已经有了
                boolean flag = true;
                for (List<String> strings : list) {
                    String index = strings.get(0);
                    char[] chars = str.toCharArray();
                    //判断是否全部配置
                    boolean has = true;
                    if(chars.length==0){
                        has=false;
                        if(index=="")
                            has=true;
                    }
                    for (char aChar : chars) {
                        if(!index.contains(String.valueOf(aChar))){
                            has=false;
                            break;
                        }
                    }

                    if(has){
                        strings.add(str);
                        flag=false;
                    }
                }

                if(flag){
                    List<String> array  = new ArrayList();
                    array.add(str);
                    list.add(array);
                }
            }
        }
        System.out.println(list.toString());
        return list;
    }

    // 每个字符串 排序后 都是 一样的 .通过此来判断
    public List<List<String>> groupAnagrams2(String[] strs) {

        /*Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String word = String.valueOf(chars);
            if(!map.containsKey(word)) {
                map.put(word, new ArrayList<>());
            }
            map.get(word).add(str);
        }
        return new ArrayList<>(map.values());*/

        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sored = String.valueOf(chars);
            if(map.containsKey(sored))
                map.put(sored,new ArrayList<>());
            map.get(sored).add(str);
            //写的 比较繁琐, 判断在操作比较繁琐,
            /*if(map.get(sored)!=null){
                List<String> array=map.get(sored);
                array.add(str);
                map.put(sored,array);
            }else {
                List<String> array = new ArrayList<>();
                array.add(str);
                map.put(sored,array);
            }*/
        }

        // 不需要在 迭代 map
        /*List list = new ArrayList();
        for (List<String> value : map.values()) {
            list.add(value);
        }

        System.out.println(list.toString());*/
        return new ArrayList<>(map.values());
    }
}
