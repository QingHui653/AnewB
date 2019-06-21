package test.core.dataStructure.Tree.RBTree;

import lombok.ToString;

/**
 * RB树节点
 */
@ToString(exclude = {"parent"})
public class RBNode<T extends Comparable> {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    public RBNode<T> left;//左结点

    public RBNode<T> right;//右结点

    public RBNode<T> parent;//父节点

    public T data;

    public Boolean color = RED;

    public RBNode(T data) {
        this.data = data;
    }
}
