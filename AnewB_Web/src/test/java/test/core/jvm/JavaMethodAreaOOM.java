package test.core.jvm;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 方法区存放Class相关的信息，比如类名、访问修饰符、常量池、字段描述、方法描述等。
 * 对于方法区的内存溢出的测试，基本思路是在运行时产生大量类字节码区填充方法区。
 * 这里引入Spring框架的CGLib动态代理的字节码技术，通过循环不断生成新的代理类，达到方法区内存溢出的效果。
 *
 * 测试结果分析：
 *
 * JDK1.6版本运行结果显示常量池会溢出并抛出永久带的OutOfMemoryError异常。
 * 而JDK1.7及以上的版本则不会得到相同的结果，它会一直循环下去。
 */
/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });

            enhancer.create();
        }
    }

    private static class OOMObject {
        public OOMObject() {
        }
    }
}
