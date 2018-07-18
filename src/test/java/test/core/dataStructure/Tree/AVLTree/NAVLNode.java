package test.core.dataStructure.Tree.AVLTree;


import lombok.ToString;
import org.junit.Test;

import java.util.Hashtable;
import java.util.TreeMap;

@ToString
public class NAVLNode<T extends Comparable> {
    private AVLNode<T> root;


    public void insert(T n){
        root = insert(root,n);
    }
    public AVLNode<T> insert(AVLNode<T> node,T n){
        if(node==null)
            node=new AVLNode(n);
        int result = n.compareTo(node.data);

        if(result<0){
            node.left = insert(node.left,n);

            if(height(node.left)-height(node.right)>=0){

                //判断是否 插入左孩子
                if(n.compareTo(node.left.data)<0){
                    //左旋即可
                    leftHanded(node);
                }else {
                    // 左旋+右旋
                    node = leftHanded(node);
                    node = rightHanded(node);
                }
            }

        }else if(result>0){
            node.right = insert(node.right,n);

            if(height(node.left)-height(node.right)>=0){
                //判断是否 插入左孩子
                if(n.compareTo(node.right.data)>0){
                    //右旋 即可
                    rightHanded(node);
                }else {
                    //右旋+左旋
                    node = rightHanded(node);
                    node = leftHanded(node);
                }
            }
        }

        return  node;
    }

    public int height(AVLNode<T> p){
        return p == null ? -1 : p.height;
    }

    //左旋
    public AVLNode<T> leftHanded(AVLNode x){
        //右子作父，父为左子，左孙变右孙
        AVLNode<T> w=  x.right;
        x.right = w.left;
        w.left=x;
        //重新计算x/w的高度
        x.height=Math.max(height(x.left),w.height)+1;
        w.height=Math.max(height(w.left),height(w.right))+1;
        return w;
    }

    //右旋
    public AVLNode<T> rightHanded(AVLNode x){
        //左子作父，父为右子，右孙变左孙
        AVLNode<T> w =  x.left;
        x.left= w.right;
        w.right=x;
        //重新计算x/w的高度
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(height(w.left),x.height)+1;
        return w;
    }

    //get
    public AVLNode<T> get(T n){
        return get(root,n);
    }
    public AVLNode<T> get(AVLNode node,T n){
        AVLNode<T> res= null;

        if(node==null) return res;

        if(node.data==n)
            res = node;
        else if(node.data.compareTo(n)>0)
            res = get(node.left,n);
        else if(node.data.compareTo(n)<0)
            res = get(node.right,n);

        return res;
    }

    //remove
    public void remove(T n){
        root = remove(root,n);
    }
    public AVLNode<T> remove(AVLNode node,T n){

        return null;
    }


    @Test
    public void testAVL(){
        NAVLNode avlTree = new NAVLNode();
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(6);
        avlTree.insert(2);
        avlTree.insert(4);
        avlTree.insert(1);

        System.out.println(avlTree.toString());

        avlTree.root = rightHanded(avlTree.root);

        System.out.println(avlTree);
    }
}
