package test.core.dataStructure.recursion;

public class RecursionLeetCode {

    public static void main(String[] args) {
//        char[] s = {'h','e','l','l','o'};
        char[] s = {'H','a','n','n','a','h'};
        reverseString(s);
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     *
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     */

    public static void reverseString(char[] s) {
//        helper(0,s);
        int len = s.length;
        int idx = 0;
        char tmp;
        while(idx < len-1-idx) {
            tmp = s[idx];
            s[idx] = s[len-1-idx];
            s[len-1-idx] = tmp;
            idx++;
        }
        return;
    }

    private static void helper(int index,char[] s){
        if(s==null || index >=s.length/2)
            return;
        helper(index+1,s);
        char tmp = s[s.length-index-1];
        s[s.length-index-1]=s[index];
        s[index] = tmp;
    }
}
