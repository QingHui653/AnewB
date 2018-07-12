package test.core.dataStructure.Tree.nTree;

import lombok.ToString;

import javax.enterprise.inject.New;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@ToString
public class NewTree {

    private NewNode root;

    /**
     * put
     * @param n
     */
    public void put(Integer n){
        root = insert(root,n);
    }


    public NewNode insert(NewNode node, Integer n){
        if(node==null)
            node=new NewNode(n);
        // data 小于 插入为 左节点
        if (node.data > n)
            node.leftNode = insert(node.leftNode,n);
        else if(node.data < n)
            node.rightNode= insert(node.rightNode,n);

        return  node;
    }

    /**
     * remove
     * @param n
     */
    public void remove(Integer n){
        root = remove(root,n);
    }

    //分为两种情况 是否有左右树 或 单独左右子树
    //1. 左右都有 root 节点 需要 保证在 左右的 值中间(左树 最大值,或右树最小值)
    //2.
    public NewNode remove(NewNode node, Integer n){
        if(node==null)
            return node;

        if (node.data > n){
            node.leftNode = remove(node.leftNode,n);
        }else if(node.data < n){
            node.rightNode= remove(node.rightNode,n);
        //左右都有
        }else if(node.leftNode!=null && node.rightNode!=null){
            //替换为 最小值 然后 移除 节点 查左树
//            node.data=findMax(node.leftNode).data;
//            node.leftNode=remove(node.leftNode,node.data);
            //查右树
            node.data=findMin(node.rightNode).data;
            node.rightNode=remove(node.rightNode,node.data);
        //单独子树
        }else {
            //判断是否是左树存在
            node=(node.leftNode!=null)?node.leftNode:node.rightNode;
        }
        return  node;
    }


    /**
     * get
     * @param n
     * @return
     */
    public Integer get(Integer n){
        return get(root,n);
    }

    public Integer get(NewNode node, Integer n){
        Integer res= null;

        if(node==null) return res;

        if(node.data==n)
            res = node.data;
        else if(node.data>n)
            res = get(node.leftNode,n);
        else if(node.data<n)
            res = get(node.rightNode,n);

        return res;
    }



    private NewNode findMin(){
        return findMin(root);
    }
    private NewNode findMin(NewNode node){
        if(node==null)
            return null;
        else if(node.leftNode==null)
            return node;
        return node.leftNode;
    }

    private NewNode findMax(){
        return findMax(root);
    }
    private NewNode findMax(NewNode node){
        if(node==null)
            return null;
        else if(node.rightNode==null)
            return node;
        return node.rightNode;
    }

    //使用 root 节点的遍历 顺序来命名
    //中左右
    public String preOrder(){
        return preOrder(root);
    }
    public String preOrder(NewNode node){
        StringBuilder sb=new StringBuilder();
        if(node!=null){
            sb.append(node.data).append(",");
            sb.append(preOrder(node.leftNode));
            sb.append(preOrder(node.rightNode));
        }
        return sb.toString();
    }

    //非递归 前序
    public String preOrderTraverse(){
        Stack stack = new Stack();
        StringBuilder sb=new StringBuilder();
        NewNode p =this.root;
        while (p!=null || !stack.isEmpty()){
            if(p!=null) {
                sb.append(p.data).append(",");

                stack.push(p);

                p=p.leftNode;
            }else {
                p=(NewNode) stack.pop();
                p=p.rightNode;
            }
        }

        return sb.toString();
    }

    //左中右
    public String inOrder(){
        return inOrder(root);
    }
    public String inOrder(NewNode node){
        StringBuilder sb=new StringBuilder();
        if(node!=null){
            sb.append(inOrder(node.leftNode));
            sb.append(node.data).append(",");
            sb.append(inOrder(node.rightNode));
        }
        return sb.toString();
    }

    //非递归 中序
    public String inOrderTraverse(){
        Stack stack = new Stack();
        StringBuilder sb=new StringBuilder();
        NewNode p =this.root;
        while (p!=null || !stack.isEmpty()){
            while (p!=null){ //将 左孩子全部入栈
                stack.push(p);
                p=p.leftNode;
            }

            if(!stack.isEmpty()){
                p = (NewNode) stack.pop();
                sb.append(p.data).append(",");
                p= p.rightNode;
            }
        }

        return sb.toString();
    }

    //左右中
    public String postOrder(){
        return postOrder(root);
    }
    public String postOrder(NewNode node){
        StringBuilder sb=new StringBuilder();
        if(node!=null){
            sb.append(postOrder(node.leftNode));
            sb.append(postOrder(node.rightNode));
            sb.append(node.data).append(",");
        }
        return sb.toString();
    }

    //非递归 后序
    public String postOrderTraverse(){
        Stack stack = new Stack();
        StringBuilder sb=new StringBuilder();

        NewNode currentNode =this.root;
        NewNode prev=this.root;

        while (currentNode!=null || !stack.isEmpty()){
            //将 左孩子全部入栈
            while (currentNode!=null){
                stack.push(currentNode);
                currentNode=currentNode.leftNode;
            }

            if(!stack.isEmpty()){
                //获取右孩子
                NewNode top = (NewNode) stack.peek();
                NewNode rightNode = top.rightNode;
                //先判断是否有右孩子或者右孩子是否已被访问过
                if(rightNode==null|| rightNode==prev){//没有右孩子||右孩子已被访问过
                    //如果没有右孩子或者右孩子已被访问,则弹出父结点并访问
                    currentNode = (NewNode) stack.pop();
                    sb.append(currentNode.data+",");
                    //记录已访问过的结点
                    prev=currentNode;
                    //置空当前结点
                    currentNode=null;

                }else { //遍历右树 的 左孩子
                    currentNode=rightNode;
                }
            }
        }

        return sb.toString();
    }

    //层序遍历 非递归
    public String sequence(){
        return sequence(root);
    }
    public String sequence(NewNode node){
        Queue queue = new LinkedList();
        StringBuilder sb=new StringBuilder();
        NewNode p =node;
        while (p!=null){
            sb.append(p.data).append(",");

            if(p.leftNode!=null)
                queue.add(p.leftNode);

            if(p.rightNode!=null)
                queue.add(p.rightNode);

            p = (NewNode)queue.poll();
        }

        return sb.toString();
    }
}
