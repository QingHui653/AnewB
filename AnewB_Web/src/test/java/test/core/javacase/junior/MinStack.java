package test.core.javacase.junior;

/**
 * @Auther:b
 * @Date: 2018/12/20
 * @Deseription
 */

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
//栈 为 先进后出,栈顶为 待出
public class MinStack {
    /*private List<Integer> nums;
    private Integer length=0;

    public MinStack() {
        nums=new ArrayList<>();
    }
    public void push(int x) {
        nums.add(x);
        length++;
    }
    public void pop() {
        nums.remove(length-1);
        length--;
    }
    public int top() {
        return nums.get(length-1);
    }
    public int getMin() {
        Integer min=null;
        for (Integer num : nums) {
            if(min==null) min =num;
            if(num<min) min=num;
        }
        return min;
    }*/
    Stack<Integer> dataStack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack=new Stack<>();
        minStack=new Stack<>();
    }

    public void push(int x) {
        dataStack.push(x);
        if(minStack.isEmpty()||x<=minStack.peek()){
            minStack.push(x);
        }
    }

    public void pop() {
        int y=dataStack.pop();
        if(minStack.peek()==y){
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

    /**
     * [null,null,null,null,-3,null,0,-2]
     * @param args
     */
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
