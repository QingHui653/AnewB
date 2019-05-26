package test.core.javacase.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @Auther:woshizbh
 * @Date: 2018/9/30
 * @Deseription
 */
public class TreeTest {

    @Test
    public void test(){
        TreeNode t1= new TreeNode(3);
        TreeNode t2= new TreeNode(9);
        TreeNode t3= new TreeNode(20);
        TreeNode t4= new TreeNode(15);
        TreeNode t5= new TreeNode(7);

        t1.left=t2;
        t1.right=t3;
        t3.left=t4;
        t3.right=t5;
//        System.out.println(maxDepth(t1));

        TreeNode a1= new TreeNode(10);
        TreeNode a2= new TreeNode(5);
        TreeNode a3= new TreeNode(15);
        TreeNode a4= new TreeNode(6);
        TreeNode a5= new TreeNode(20);
        a1.left=a2;
        a1.right=a3;
        a3.left=a4;
        a3.right=a5;
        System.out.println(isValidBST(a1));

        System.out.println(levelOrder(t1));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return maxDepth(root,1);
    }

    public int maxDepth(TreeNode root,int depth) {
        if(root==null) return depth;
        int left=0,right=0;

        if(root.left==null && root.right==null)
            return depth;

        depth+=1;

        if(root.left!=null)
            left = maxDepth(root.left,depth);

        if(root.right!=null)
            right = maxDepth(root.right,depth);

        return left>=right?left:right;
    }

    public int maxDepth1(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);
        return left>=right?left+1:right+1;
    }

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     */
    //第一次做只判断了 父子节点,未判断 祖孙节点
    // 递归检测每颗树
    // 或 中序遍历
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        ArrayList<Integer> list = new ArrayList<>();
        middleOrder(root, list);

        for(int i= 0;i<list.size() - 1;i++){
            if(list.get(i) < list.get(i+1)) continue;
            else return false;
        }
        return true;

        //递归
//        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    // 中序遍历
    public static void middleOrder(TreeNode root, ArrayList<Integer> list){
        if(root!= null){
            middleOrder(root.left, list);
            list.add(root.val);
            middleOrder(root.right, list);
        }

    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    // 使用栈
    public List<List<Integer>> levelOrder(TreeNode root) {
        List all = new ArrayList();
        if(root==null) return all;


        Stack<TreeNode> stack = new Stack();
        stack.push(root);

        Stack<TreeNode> stash = new Stack<>();

        while (!stack.empty()) {

            List level = new ArrayList();
            while (!stack.empty() ) {
                TreeNode node = stack.pop();
                if(node!=null) {
                    level.add(node.val);
                    stash.push(node.left);
                    stash.push(node.right);
                }
            }

            while (!stash.empty()) {
                TreeNode child = stash.pop();
                if(child!=null)
                    stack.push(child);
            }

            all.add(level);
        }

        return all;
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> l=new ArrayList<List<Integer>>();
        addLevel(l,0,root);
        //Collections.reverse(l);
        return l;
    }

    public void addLevel(List<List<Integer>> list, int level, TreeNode node){
        if(node==null)return ;
        if(list.size()-1<level)list.add(new ArrayList<Integer>());
        list.get(level).add(node.val);

        addLevel(list,level+1,node.left);
        addLevel(list,level+1,node.right);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();

                temp.add(root.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }


            ans.add(temp);
        }

        return ans;
    }

    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定有序数组: [-10,-3,0,5,9],
     *
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @param nums
     * @return
     */
    // 已经是有序, 直接取中值做完 root ,然后左边为左树,右边做右树
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new TreeNode(nums[start]);
        } else {
            int mid = (start + end) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBST(nums, start, mid - 1);
            root.right = sortedArrayToBST(nums, mid + 1, end);
            return root;
        }
    }
}
