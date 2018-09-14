package test.core.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * xmx 最小 xmx 最大 -XX:+HeapDumpOnOutOfMemoryError 内存溢出异常时Dump出内存堆运行时快照
 * xmx 与 xmx 最好一样 防止Java堆在内存不足时自动扩容。
 */
public class HeapOOM {
    public static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
