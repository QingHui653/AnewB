package test.core.jvm;

/**
 * VM Args: -Xss2M
 * 不停地创建线程并保持线程运行状态。
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 */
public class JavaVMStackOOM {
    private void running() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    running();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
