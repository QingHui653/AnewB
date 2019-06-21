package newb.c.a_spring.a_module.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * @Auther:woshizbh
 * @Date: 2018/10/15
 * @Deseription
 */
public class testPreconditions {

    @Test
    public void test(){
        System.out.println(sum(null,3));
    }

    public int sum(Integer a, Integer b){
        a = Preconditions.checkNotNull(a, "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b, "Illegal Argument passed: Second parameter is Null.");
        return a+b;
    }
}
