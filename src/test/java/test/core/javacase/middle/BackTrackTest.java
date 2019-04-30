package test.core.javacase.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther:woshizbh
 * @Date: 2019/4/22
 * @Deseription
 */
public class BackTrackTest {

    @Test
    public void test(){
//        System.out.println(letterCombinations("23").toString());
        System.out.println(generateParenthesis(3).toString());
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 示例:
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 2 abc 3 def 4 ghi 5 jkl 6mno 7 qprs 8 tuv 9wxyz
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     * @param digits
     * @return
     */
    // 暴力破解 没法判断循环的 层数
    //https://blog.csdn.net/hk2291976/article/details/51259951
    // 递归
    // 假定有个数字串“23456”
    //- 假定除了数字’2’外，后一串数字的组合我已经求出来了，那我只要把‘2’所代表的’abc’加到他们每一个的前面就好。所以现在只用求数字串”3456”
    //- 假定’3’之后一串数字的组合我已经求出来了，那我只要把‘3’所代表的’def’加到他们每一个的前面就好。所以现在只用求数字串”456”
    //- 一直这样推下去，直到发现6’后面是空的了，那返回它代表的每个字母就好了。
    // 队列 (例如23, 从"" 开始,加工,获取到 a b c 在 加工 a-> ad,ae,af b-> bd be bf 等等.)
    public List<String> letterCombinations(String digits) {
        LinkedList<String> result = new LinkedList<>();
        if (digits == null || digits.isEmpty()) return result;
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            // 字符 转 数字
            int pos = digits.charAt(i)-'0';
            String s = map[pos];
            int size = result.size();
            for (int j = 0; j < size; j++) {
                String tmp = result.poll();
                for (int k = 0; k < s.length(); k++)
                    result.add(tmp + s.charAt(k));
            }
        }
        return result;
    }

    public static List<String> letterCombinations2(String digits) {
        String digitletter[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList<String>();
        if (digits.length()==0) return result;
        result.add("");
        for (int i=0; i<digits.length(); i++)
            result = combine(digitletter[digits.charAt(i)-'0'],result);

        return result;
    }

    public static List<String> combine(String digit, List<String> l) {
        List<String> result = new ArrayList<String>();

        for (int i=0; i<digit.length(); i++)
            for (String x : l)
                result.add(x+digit.charAt(i));

        return result;
    }

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * 例如，给出 n = 3，生成结果为：
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     * @param n
     * @return
     */
    // 有效的 括号(保证每一个 括号都能封闭)
    //n个左右括号 , 左右括号都用完为一个解.左括号的数不能大于右括号
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        dfs(res, "", n, 0, 0);
        return res;
    }
    // DFS 深度遍历 (回溯算法的 一种): 主题思想: 不撞南墙不回头 ;
    // 重要 https://blog.csdn.net/chensanwa/article/details/79717835
    //伪代码
    /**
     * DFS(dep,、、、）        //dep代表目前DFS的深度
     * {
     *     if(找到解 || 走不下去){
     *         、、、     //在此处进行相应的操作
     *         return ;
     *     }
     *     枚举下一种情况，DFS（dep+1,、、、）
     * }
     */
    private void dfs(List<String> res, String temp, int n, int left, int right) {
        if(right == n) {
            res.add(temp);
            return;
        }
        if (left < n) dfs(res, temp+"(", n, left+1, right);
        if (right < left) dfs(res, temp+")", n, left, right+1);
    }

}
