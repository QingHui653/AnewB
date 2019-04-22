package test.core.javacase.middle;

import org.junit.Test;

import java.util.*;

/**
 * @Auther:b
 * @Date: 2019/3/29
 * @Deseription
 */
public class TreeTest {

    @Test
    public void test() {
        TreeNode treeNode0 = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode0.right = treeNode1;
        treeNode1.left = treeNode2;

//        inorderTraversal(treeNode0);
//        inorderTraversal2(treeNode0);

        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
//        System.out.println(zigzagLevelOrder(t1));

        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
//        TreeNode buildTree = buildTree(pre, in);
//        System.out.println(buildTree);

        System.out.println(kthSmallest3(t1, 4));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * 示例:
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
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

    private List<Integer> mid(TreeNode root, List<Integer> list) {
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
        while (p != null || !stack.isEmpty()) {

            while (p != null) { //将 左孩子全部入栈
                stack.push(p);
                p = p.left;
            }

            if (!stack.isEmpty()) {
                p = stack.pop();
                list.add(p.val);
                p = p.right;
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
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回锯齿形层次遍历如下：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    // 先从 左开始.
    // 感觉要定义两个栈,一个存放当前 层,一个存放下一层.
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        if (root == null) return list;

        List<Integer> lever = new ArrayList();
        //存放从左到右的;右边先进
        Stack<TreeNode> firstLever = new Stack();
        //存放从右到左的;左边先进
        Stack<TreeNode> lastLever = new Stack();
        firstLever.push(root);

        while (!firstLever.isEmpty() || !lastLever.isEmpty()) {

            while (!firstLever.isEmpty()) {
                TreeNode cur = firstLever.pop();
                if (cur != null) {
                    lever.add(cur.val);
                    lastLever.push(cur.left);
                    lastLever.push(cur.right);
                }
            }
            if (lever.size() != 0) {
                list.add(lever);
                lever = new ArrayList<>();
            }
            while (!lastLever.isEmpty()) {
                TreeNode cur = lastLever.pop();
                if (cur != null) {
                    lever.add(cur.val);
                    firstLever.push(cur.right);
                    firstLever.push(cur.left);
                }
            }
            if (lever.size() != 0) {
                list.add(lever);
                lever = new ArrayList<>();
            }
        }

        return list;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        dfs(lists, root, 0);
        for (int i = 1; i < lists.size(); i += 2) {
            List<Integer> list = new ArrayList<>();
            for (int j = lists.get(i).size() - 1; j >= 0; j--) {
                list.add(lists.get(i).get(j));
            }
            lists.set(i, list);
        }
        return lists;
    }

    public void dfs(List<List<Integer>> lists, TreeNode root, int level) {
        if (lists.size() <= level) {
            List<Integer> list = new ArrayList<>();
            lists.add(list);
        }
        lists.get(level).add(root.val);
        if (root.left != null)
            dfs(lists, root.left, level + 1);
        if (root.right != null)
            dfs(lists, root.right, level + 1);
    }

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param preorder
     * @param inorder
     * @return
     */
    // 完全没点思路
    // 参考 https://www.cnblogs.com/glamourousGirl/p/3771001.html
    // https://blog.csdn.net/u011452172/article/details/78246416

    /**
     * 前序第一个为根节点.因为无重复,
     * 在 中序 遍历一遍 可以找出 根位置和 index , 中序数组 就分为两部分,左为左子树,右为右子树.
     * 在前序中, 左子树的 位置 为 中序左子树的 数量 , 右子树位置为 中序右子树 数量.
     * 然后在 递归调用
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return buildTreeRecursive(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    //前序数组,前序树的 start和end
    //中序数组 中序树的 start和end
    public TreeNode buildTreeRecursive(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        int value = preOrder[preStart];
        //1、先序遍历中的第一个节点一定是根节点。
        TreeNode node = new TreeNode(value);
        //结束条件：如果长度为1，则返回该节点。
        if (preStart == preEnd) return node;

        //2、在中序遍历中查找该节点的位置。
        int index = 0;
        for (index = inStart; index <= inEnd && inOrder[index] != value; ) {
            index++;
        }


        //3、确定左右子树对应数组的位置后，递归调用。
        int leftLen = index - inStart;
        int rightLen = inEnd - index;

        if (leftLen > 0) {
            node.left = buildTreeRecursive(preOrder, preStart + 1, preStart + leftLen, inOrder, inStart, index - 1);
        }
        if (rightLen > 0) {
            node.right = buildTreeRecursive(preOrder, preEnd - rightLen + 1, preEnd, inOrder, index + 1, inEnd);
        }

        return node;
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/88/
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * <p>
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * 你只能使用常量级额外空间。
     * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度
     *
     * @param root
     * @return
     */
    // 感觉像是 层序遍历.
    public Node connect(Node root) {
        if (root == null) return null;
        //当左节点 不为 null
        if (root.left != null) {
            // 左节点的1next 肯定指向 右节点
            root.left.next = root.right;
            //当 root.next 不为 null ,及root已经是左树中的节点.
            //所有root的右节点.肯定指向 next的 左节点
            if (root.next != null) root.right.next = root.next.left;
        }
        //遍历 左右节点
        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     * 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * <p>
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     * 2
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     *
     * @param root
     * @param k
     * @return
     */
    //1.简单的暴力: 遍历,排序
    //  二叉搜索树 ,中序遍历 就是排序.不需要使用前序在排序.
//    public int kthSmallest(TreeNode root, int k) {
//        if(root==null) return 0;
//        List<Integer> list = new ArrayList();
//        firstSort(root,list);
//        Object[] objects = list.toArray();
//        Arrays.sort(objects);
//        return (int)objects[k-1];
//    }
//
//    public void firstSort(TreeNode root,List list) {
//        if(root!=null){
//            list.add(root.val);
//            firstSort(root.left,list);
//            firstSort(root.right,list);
//        }
//    }
    //2. 前序 全部 遍历 在 排序.
    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        mid2(root, list);
        return list.get(k - 1);
    }

    public void mid2(TreeNode root, List<Integer> list) {
        if (root != null) {
            mid2(root.left, list);
            list.add(root.val);
            mid2(root.right, list);
        }
    }

    // 3. 前序不需要遍历完.只需 遍历到 查找的位置即可
    public int kthSmallest3(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        visit(root, list, k);
        return list.get(k - 1);
    }

    public void visit(TreeNode root, List<Integer> list, int k) {
        if (root != null && list.size() < k) {
            visit(root.left, list, k);
            list.add(root.val);
            visit(root.right, list, k);
        }
    }

    /**
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * <p>
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * <p>
     * 输出: 3
     *
     * @param grid
     * @return
     */
    //暴力: 当 1 上下左右全是 0,则认为是 一个岛.
    // 使用DFS 深度优先遍历 .
    /**
     * 题目要求，1表示小岛，0表示海，找出所有的小岛；一开始还没有思路，看了别人的博客说用dfs一下子想清楚了，
     * 用一个嵌套循环对每个点进行判断，如果当前值为1的话，那么调用dfs方法，将它连接到部分的1全部置为0，
     * 这样在嵌套的过程中记录出现1的个数，在dfs会把它相邻的1全部去掉，所以不会出现多加或者少加的情况。
     */
    //了解 DFS 与 BFS
    //深度遍历.遇到1 ,将1周围的 全变为 0,递归.这样 就会只剩下一个1.
    //将 大岛 化为 只有 1个1 的 小岛.
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    //System.out.println("find 1");
                    count++;
                }

                dfs(i, j, grid);
            }
        }
        return count;
    }

    public void dfs(int i, int j, char[][] grid) {
        if (grid[i][j] == '1') {
            grid[i][j] = '0';

            if (i > 0)
                dfs(i - 1, j, grid);
            if (i < grid.length - 1)
                dfs(i + 1, j, grid);
            if (j > 0)
                dfs(i, j - 1, grid);
            if (j < grid[0].length - 1)
                dfs(i, j + 1, grid);
        } else return;
    }

}