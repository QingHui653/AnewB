package test.core.dataStructure.Tree.RBTree;

import lombok.ToString;
import org.junit.Test;

/**
 * 默认传入 类型为 int 其他未处理
 * @param <T>
 * // ...参考 treeMap
 */
@ToString
public class RBTree<T extends Comparable> {

    public RBNode<T> root;

    public void insert(T data){
        RBNode node = new RBNode(data);
        insert(node);
    }

    public void remove(T data){
        RBNode node = getNode(data);
        if(node==null) return;
        remove(node);
    }

    public RBNode insert(RBNode node){
        int cmp;
        RBNode<T> y = null;
        RBNode<T> x = this.root; //root

        //按照二叉树找到 x
        while (x!=null){
            // 找到待插入的 节点
            y = x;
            cmp = node.data.compareTo(x.data);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }
        // 赋值 父节点
        node.parent = y;

        if (y!=null) {
            cmp = node.data.compareTo(y.data);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        } else {
            //吓的变色
            node.color=true;
            return this.root=node;
        }

        // 旋转变色
        insertFixUp(node);

        return  y;
    }

    /**
     * 先删除,在变色
     */
    public RBNode remove(RBNode node){
        //删除 元素 node
        //变色
        deleteFixUp(this.root);
        return node;
    }
    // 旋转变色
    //插入 分为 五种情况
    private void insertFixUp(RBNode node) {
        RBNode<T> parent, gparent;
        parent=node.parent;
        //第一种 只有 ROOT 节点.直接 变色 在上面已处理
        //第二种 插入 节点为 黑色 不做处理
        while (!parent.color){ //只处理 红色节点
            gparent=node.parent.parent;
            // 父节点 为 祖 节点的 左孩子
            if(parent==gparent.left){
                RBNode<T> uncle = gparent.right;
                // case 1 : 父节点 为红色 叔节点为 红色 (将父叔 染黑 ,祖染红,然后递归向上调整)
                if(uncle!=null && !uncle.color){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                //case 2 : 叔 为 黑色 且当前 节点为 右孩子
                if(parent.right==node){
                    node=leftHanded(node);
                }

                //case 3 : 叔为 黑色 且 当前 节点为 左孩子
                setBlack(parent);
                setRed(gparent);
                node = rightHanded(gparent);
            }else {
                RBNode<T> uncle = gparent.left;
                // case 1 : 父节点 为红色 叔节点为 红色 (将父叔 染黑 ,祖染红,然后递归向上调整)
                if(uncle!=null && !uncle.color){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                //case 2 : 叔 为 黑色 且当前 节点为 左孩子
                if(parent.left==node){
                    node=rightHanded(node);
                }

                //case 3 : 叔为 黑色 且 当前 节点为 右孩子
                setBlack(parent);
                setRed(gparent);
                node = leftHanded(gparent);
            }
        }

        //根节点始终为黑色
        setBlack(this.root);
    }

    private void deleteFixUp(RBNode node){

    }

    private RBNode getNode(T data) {
        if(root==null) return null;
        RBNode p = root;
        while (p!=null){
            int cmp = p.data.compareTo(data);

            if(cmp<0)
                p=p.left;
            else if ( cmp>0 )
                p=p.right;
            else
                return p;
        }
        return null;
    }

    //左旋
    public RBNode<T> leftHanded(RBNode x){
        //右子作父，父为左子，左孙变右孙getOrdersSkuByFinancing
        RBNode<T> w=  x.right;
        x.right = w.left;
        //如果 left 非空 修改 父节点
        if(w.left!=null)
            w.left.parent=x;
        //修改 右子的 父节点
        w.parent = x.parent;

        // 修改 父节点的 孩子
        if(x.parent==null){
            this.root=w;
        } else {
            if(x.parent.left==x) //判断 是否为 父节点的 左孩子
                x.parent.left=w;
            else
                x.parent.right=w;
        }

        w.left=x;
        // 将x 的 父节点 设为 w
        x.parent=w;
        return w;
    }

    //右旋
    public RBNode<T> rightHanded(RBNode x){
        //左子作父，父为右子，右孙变左孙
        RBNode<T> w =  x.left;
        x.left= w.right;

        if(w.right!=null)
            w.right.parent=x;

        w.parent=x.parent;

        if(x.parent==null){
            this.root=w;
        }else {
            if(x.parent.left==x) //判断 是否为 父节点的 左孩子
                x.parent.left=w;
            else
                x.parent.right=w;
        }

        w.right=x;
        x.parent=w;
        return w;
    }

    public void setBlack(RBNode node){
        node.color=true;
    }

    public void setRed(RBNode node){
        node.color=false;
    }

    @Test
    public void testRBTree(){
        // 测试 插入 始终是在 同一边的.
        RBTree rbTree = new RBTree();
        rbTree.insert(1);
        rbTree.insert(2);
        rbTree.insert(3);
        rbTree.insert(4);
        rbTree.insert(5);

        // 左旋 右旋 未判断 右树 为null  暂时不做了
        RBTree rbTree2 = new RBTree();
        rbTree2.insert(16);
        rbTree2.insert(3);
        rbTree2.insert(7);
        //16, 3, 7, 11, 9, 26, 18, 14, 15

        System.out.println(rbTree2.toString());
    }
}
