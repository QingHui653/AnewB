package test.dataStructure.Tree.BinaryTree;

import java.io.Serializable;

/**
 * 自定义异常
 */
public class EmptyTreeException extends RuntimeException implements Serializable{


    private static final long serialVersionUID = -8766038608920134747L;


    public EmptyTreeException(){
        super();
    }

    public EmptyTreeException(String msg){
        super(msg);
    }
}
