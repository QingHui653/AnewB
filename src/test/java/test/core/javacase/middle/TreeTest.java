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
        inorderTraversal2(treeNode0);
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
}
