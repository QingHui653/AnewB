package test.core.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 设置JVM启动参数：通过-XX:PermSize=10M和-XX:MaxPermSize=10M限制方法区的大小为10M，从而间接的限制其中常量池的容量。
 *
 * ---
 * JDK1.6版本运行结果：
 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 *     at java.lang.String.intern(Native Method)
 * JDK1.6版本运行结果显示常量池会溢出并抛出永久带的OutOfMemoryError异常。
 * 而JDK1.7及以上的版本则不会得到相同的结果，它会一直循环下去。
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List保持着常量池的引用，避免Full GC回收常量池
        List<String> list = new ArrayList<>();
        // 10MB的PermSize在Integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
