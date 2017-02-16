package test.dataStructure.Stack;

import test.dataStructure.LinkedList.MyCollection.MyArrayList;

import java.io.Serializable;


/**
 * 顺序栈的实现(利用顺序表实现作为内部实现)
 */
public class StackByArrayList<T> implements Stack<T>,Serializable {

    private static final long serialVersionUID = -5413303117698554090L;


    private MyArrayList<T> list;

    public StackByArrayList(int capacity){
        list=new MyArrayList<T>(capacity);
    }

    public StackByArrayList(){
       list=new MyArrayList<T>();
    }


    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * 添加元素,从栈顶(顺序表尾部)插入
     * @param data
     */
    @Override
    public void push(T data) {
        this.list.add(data);
    }

    /**
     * 获取栈顶元素的值,不删除
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return this.list.get(list.size()-1);
    }

    /**
     * 从栈顶(顺序表尾部)删除
     * @return
     */
    @Override
    public T pop() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return this.list.remove(list.size()-1);
    }
}
