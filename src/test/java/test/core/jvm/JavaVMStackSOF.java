package test.core.jvm;

/**
 * VM Args: -Xss128k  设置栈内存的大小为128k
 * --
 * 使用-Xss参数减少栈内存的容量，异常发生时打印栈的深度。
 * 定义大量的本地局部变量，以达到增大栈帧中的本地变量表的长度。
 *
 * 在单个线程下，无论是栈帧太大还是虚拟机栈容量太小，当无法分配内存的时候，虚拟机抛出的都是StackOverflowError异常。
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    private void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack length: " + oom.stackLength);
            throw e;
        }
    }
}
