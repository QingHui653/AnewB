package test.core.javacase.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序,中序,后序的区别是根的访问顺序.
 * 先访问根,在子节点:则是前序
 * 先访问左(右),在根,最后右(左):则是中序
 * 先访问左右,最后根:则是后序
 *
 * 二叉树的问题难点在于，如何把题目的要求细化成每个节点需要做的事情
 *
 * https://labuladong.gitee.io/algo/2/18/22/
 *
 * 递归算法的关键要明确函数的定义，相信这个定义，而不要跳进递归细节。
 *
 * 写二叉树的算法题，都是基于递归框架的，我们先要搞清楚 root 节点它自己要做什么，然后根据题目要求选择使用前序，中序，后续的递归框架。
 *
 * 二叉树题目的难点在于如何通过题目的要求思考出每一个节点需要做什么，这个只能通过多刷题进行练习了
 *
 * @Auther:woshizbh
 * @Date: 2019/3/29
 * @Deseription
 */
public class TreeTest {

    @Test
    public void test() {
        Node treeNode1 = new Node(1);
        Node treeNode2 = new Node(2);
        Node treeNode3 = new Node(3);
        Node treeNode4 = new Node(4);
        Node treeNode6 = new Node(6);
        Node treeNode7 = new Node(7);
        Node treeNode9 = new Node(9);
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;

        treeNode7.left = treeNode6;
        treeNode7.right = treeNode9;

        treeNode4.left = treeNode2;
        treeNode4.right = treeNode7;
        //前序
        List<Integer> res = new ArrayList<>();
        preViewTree(res,treeNode4);
        System.out.println(res.toString());
        //中序
        res.clear();
        midViewTree(res,treeNode4);
        System.out.println(res.toString());
        //后序
        res.clear();
        lastViewTree(res,treeNode4);
        System.out.println(res.toString());

        res.clear();
        nodeReverse(treeNode4);
        preViewTree(res,treeNode4);
        System.out.println(res.toString());
    }

    /**
     * 递归:中序遍历二叉树
     */
    public void midViewTree(List<Integer> res, Node node){
        if(node==null) return;
        midViewTree(res,node.left);
        /**** 中序遍历位置 ****/
        res.add(node.val);
        midViewTree(res,node.right);
    }

    /**
     * 递归:前序遍历二叉树
     */
    public void preViewTree(List<Integer> res, Node node){
        if(node==null) return;
        /**** 前序遍历位置 ****/
        res.add(node.val);
        preViewTree(res,node.left);
        preViewTree(res,node.right);
    }

    /**
     * 递归:前序遍历二叉树
     */
    public void lastViewTree(List<Integer> res, Node node){
        if(node==null) return;
        lastViewTree(res,node.left);
        lastViewTree(res,node.right);
        /**** 后序序遍历位置 ****/
        res.add(node.val);
    }


    /**
     * 反转左右节点
     * @param node
     */
    public void nodeReverse(Node node){
        if(node==null) return;
        /**** 前序遍历位置 ****/
        Node nodeTmp = node.left;
        node.left=node.right;
        node.right=nodeTmp;

        nodeReverse(node.left);
        nodeReverse(node.right);
    }

    /**
     * 力扣116 填充二叉树节点的右侧指针
     * 填充next使的指向下一个右侧节点,找不到则为null
     */
    /**
     * 此方法只能找出同一个根节点的右侧节点,
     * 无法找出 相同祖父节点,就是在第三层节点的右侧节点
     * @param node
     */
    public void findRightNode(Node node){
        if(node==null||node.left==null) return;
        node.left.next = node.right;
        findRightNode(node.left);
        findRightNode(node.right);
    }
    /**
     * 二叉树的问题难点在于，如何把题目的要求细化成每个节点需要做的事情
     */
    /**
     * 一个节点,无法解决,那就是有两个节点
     */
    public void connectTwoNode(Node leftNode, Node rightNode){
        if(leftNode==null|| rightNode==null ) return;
        leftNode.next = rightNode;
        //连接左节点的左右
        connectTwoNode(leftNode.left,leftNode.right);
        //连接右节点的左右
        connectTwoNode(rightNode.left,rightNode.right);
        //连接左右节点的右左
        connectTwoNode(leftNode.right,rightNode.left);
    }

    /**
     * 力扣114 二叉树展开为链表
     * 首先将子节点的左移到右.全部这样只有
     * 在将根的 right链接到左,在将右链接到最后(将原先的右子树接到当前右子树的末端)
     * @param root
     */
    public void flatten(Node root) {
        if(root==null) return;

        flatten(root.left);
        flatten(root.right);

        //1.左右子树已经被拉平为链表
        Node left = root.left;
        Node right = root.right;

        if(left==null) return;

        //2.首先将左的移到右
        root.left=null;
        root.right = left;

        //找到末端,将右子树拼接上去
        Node p = root;
        while (p.right!=null){
            p=p.right;
        }

        //拼接
        p.right = right;
    }
}