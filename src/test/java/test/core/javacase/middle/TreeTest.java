package test.core.javacase.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther:b
 * @Date: 2019/3/29
 * @Deseription
 */
public class TreeTest {

    @Test
    public void test(){
        TreeNode treeNode0 = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode0.right=treeNode1;
        treeNode1.left=treeNode2;

//        inorderTraversal(treeNode0);
//        inorderTraversal2(treeNode0);

        TreeNode t1= new TreeNode(3);
        TreeNode t2= new TreeNode(9);
        TreeNode t3= new TreeNode(20);
        TreeNode t4= new TreeNode(15);
        TreeNode t5= new TreeNode(7);

        t1.left=t2;
        t1.right=t3;
        t3.left=t4;
        t3.right=t5;
        System.out.println(zigzagLevelOrder(t1));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**
     * 给定一个二叉树，返回它的中序 遍历。
     *     示例:
     *     输入: [1,null,2,3]
     *             1
     *             \
     *             2
     *             /
     *             3
     *
     *     输出: [1,3,2]
     *     进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * @param root
     * @return
     */
    //使用递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        mid(root, list);
        return list;
    }
    private List<Integer> mid(TreeNode root,List<Integer> list) {
        if (root.left != null) mid(root.left, list);
        list.add(root.val);
        if (root.right != null) mid(root.right, list);
        return list;
    }

    //好像是 先入栈,在出栈,在 遍历右边
    //1.先全部入左孩子
    //2.在从栈中 pop出来,
    //3.在迭代 右孩子
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p!=null || !stack.isEmpty()){

            while (p!=null){ //将 左孩子全部入栈
                stack.push(p);
                p=p.left;
            }

            if(!stack.isEmpty()){
                p=stack.pop();
                list.add(p.val);
                p=p.right;
            }
        }
        System.out.println(list.toString());
        return list;
    }

//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        mid(root, list);
//        return list;
//    }
//    private List<Integer> mid(TreeNode root,List<Integer> list) {
//        if(root!=null){
//            mid(root.left, list);
//            list.add(root.val);
//            mid(root.right, list);
//        }
//        return list;
//    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    // 先从 左开始.
    // 感觉要定义两个栈,一个存放当前 层,一个存放下一层.
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        if(root==null) return list;

        List<Integer> lever = new ArrayList();
        //存放从左到右的;右边先进
        Stack<TreeNode> firstLever = new Stack();
        //存放从右到左的;左边先进
        Stack<TreeNode> lastLever = new Stack();
        firstLever.push(root);

        while (!firstLever.isEmpty() || !lastLever.isEmpty()){

            while (!firstLever.isEmpty()){
                TreeNode cur = firstLever.pop();
                if(cur!=null){
                    lever.add(cur.val);
                    lastLever.push(cur.left);
                    lastLever.push(cur.right);
                }
            }
            if(lever.size()!=0) {
                list.add(lever);
                lever = new ArrayList<>();
            }
            while (!lastLever.isEmpty()){
                TreeNode cur = lastLever.pop();
                if(cur!=null) {
                    lever.add(cur.val);
                    firstLever.push(cur.right);
                    firstLever.push(cur.left);
                }
            }
            if(lever.size()!=0){
                list.add(lever);
                lever=new ArrayList<>();
            }
        }

        return list;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root == null) return lists;
        dfs(lists, root, 0);
        for(int i=1; i<lists.size(); i+=2) {
            List<Integer> list = new ArrayList<>();
            for(int j=lists.get(i).size()-1; j>=0; j--){
                list.add(lists.get(i).get(j));
            }
            lists.set(i, list);
        }
        return lists;
    }

    public void dfs(List<List<Integer>> lists, TreeNode root, int level){
        if(lists.size() <= level){
            List<Integer> list = new ArrayList<>();
            lists.add(list);
        }
        lists.get(level).add(root.val);
        if(root.left != null)
            dfs(lists, root.left, level+1);
        if (root.right != null)
            dfs(lists, root.right, level+1);
    }

}
