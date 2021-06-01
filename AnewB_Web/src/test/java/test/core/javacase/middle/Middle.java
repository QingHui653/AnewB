package test.core.javacase.middle;

import org.junit.Test;

public class Middle {

    @Test
    public void middleTest(){

        int[] candy ={5,2,6,4,1};
        int[][] quest ={{3,1,2},{4,10,3},{3,10,100},{4,100,30},{1,3,1}};
        canEat(candy,quest);
    }

    /**
     * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
     * https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/
     * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
     *
     * 你按照如下规则进行一场游戏：
     *
     * 你从第 0 天开始吃糖果。
     * 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
     * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
     * 请你构建一个布尔型数组 answer ，满足 answer.length == queries.length 。answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；
     * 否则 answer[i] 为 false 。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
     *
     * 请你返回得到的数组 answer 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
     * 输出：[true,false,true]
     * 提示：
     * 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
     * 2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
     * 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
     * 示例 2：
     *
     * 输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
     * 输出：[false,true,true,false,false]
     *  
     *
     * 提示：
     *
     * 1 <= candiesCount.length <= 105
     * 1 <= candiesCount[i] <= 105
     * 1 <= queries.length <= 105
     * 0 <= favoriteDayi <= 109
     * 1 <= dailyCapi <= 10
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candiesCount
     * queries[i].length == 3
     * 0 <= favoriteTypei < candiesCount.length
     * @param queries
     * @return
     * 存在 candiesCount.length 种类的糖,总共 candiesCount数组和怎么多,
     * 求问题 {x,y,z}在每天最多吃z个的情况下,在 x 天吃到 y类. 且吃糖只能先从种类1开始吃(必须吃一个)
     * 1.第一种情况最简单.即使每天吃z个,在 x天也吃不到y类(x*z<(1-(y-1)类的糖果总和))
     * 2.每天吃1个 也x天也超过y类 x*1>(1-y的糖果总和)
     * 3.
     * --
     * 根据题意，第 i 天可吃到的糖果数量为 [days + 1, (days + 1) * dailyCap]，而由于糖果必须按类依次消耗，第 j 种糖果在被吃序列中的索引为 (candiesSum[j - 1], candiesSum[j]]，因此只需判断 2 个区间是否存在交集，即可得到当前答案
     * 吃糖果数量的边界为 第 i 天可吃到的糖果数量为 [days + 1, (days + 1) * z]
     * 第 y 种糖果在被吃序列中的索引为 (candiesSum[y - 1], candiesSum[y]]
     *
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length, candiesType=candiesCount.length;
        long[] candiesSum = new long[candiesType];
        //使用一个 数组 来存放 每一个type sum和
        candiesSum[0] = candiesCount[0];
        for (int i = 1; i < candiesType; i++) {
            candiesSum[i] = candiesSum[i-1] +candiesCount[i];
        }

        boolean[] booleans = new boolean[len];

        for (int i = 0; i < len; i++) {
            int type = queries[i][0],day = queries[i][1], dayLimit = queries[i][2];
            long min = type>0?candiesSum[type-1]:0;
            long max = candiesSum[type];
            if(day+1<=max && (long) (day + 1) *dayLimit>min){
                booleans[i] = true;
            }else {
                booleans[i] = false;
            }
        }

        return booleans;
    }
}
