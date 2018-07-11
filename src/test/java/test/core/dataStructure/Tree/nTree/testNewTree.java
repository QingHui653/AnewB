package test.core.dataStructure.Tree.nTree;

import org.junit.Test;

public class testNewTree {


    @Test
    public void testTree(){
        NewTree newTree = new NewTree();

        newTree.put(3);
        newTree.put(5);
        newTree.put(1);
        newTree.put(4);
        newTree.put(2);
        newTree.put(7);
        newTree.put(6);

//        System.out.println(newTree);
//        System.out.println("前序-- "+newTree.preOrder());
//        System.out.println("前序 非遍历-- "+newTree.preOrderTraverse());
//        System.out.println("中序-- "+newTree.inOrder());
//        System.out.println("中序 非遍历-- "+newTree.inOrderTraverse());
        System.out.println("后序-- "+newTree.postOrder());
        System.out.println("后序 非遍历-- "+newTree.postOrderTraverse());
        System.out.println("层序-- "+newTree.sequence());
//        System.out.println(newTree.get(4));
//        System.out.println(newTree.get(6));

        newTree.remove(5);

        System.out.println(newTree);
//        System.out.println("前序-- "+newTree.preOrder());
//        System.out.println("前序 非遍历-- "+newTree.preOrderTraverse());
//        System.out.println("中序-- "+newTree.inOrder());
//        System.out.println("中序 非遍历-- "+newTree.inOrderTraverse());
        System.out.println("后序-- "+newTree.postOrder());
        System.out.println("后序 非遍历-- "+newTree.postOrderTraverse());
        System.out.println("层序-- "+newTree.sequence());
    }
}
