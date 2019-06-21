package test.core.jvm;

/**
 * 直接通过反射获取Unsafe实例，通过反射向操作系统申请分配内存：
 * 设置JVM启动参数：-Xmx20M指定Java堆的最大内存，-XX:MaxDirectMemorySize=10M指定直接内存的大小
 */

import sun.misc.Unsafe;

import java.lang.reflect.Field;
/**
 * 直接通过反射获取Unsafe实例，通过反射向操作系统申请分配内存：
 * 设置JVM启动参数：-Xmx20M指定Java堆的最大内存，-XX:MaxDirectMemorySize=10M指定直接内存的大小。
 * ---
 * 由DirectMemory导致的内存溢出，一个明显的特征是Heap Dump文件中不会看到明显的异常信息。
 * 如果OOM发生后Dump文件很小，并且程序中直接或者间接地使用了NIO，那么就可以考虑一下这方面的问题。
 */

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
