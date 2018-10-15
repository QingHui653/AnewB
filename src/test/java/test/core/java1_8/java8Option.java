package test.core.java1_8;

import org.junit.Test;

import java.util.Optional;

/**
 * @Auther:woshizbh
 * @Date: 2018/10/15
 * @Deseription
 */
//Optional 不用担心传递 null , 将null 判断交给 程序处理
public class java8Option {

    // java8
    @Test
    public void testOption(){
        Optional<Integer> i = Optional.of(3);
        Integer j = Optional.of(3).orElse(0);
        System.out.println(sum(i.get(),j));
    }

    // guava
    @Test
    public void testGuavaOption(){
        com.google.common.base.Optional<Integer> i = com.google.common.base.Optional.of(3);
        com.google.common.base.Optional<Integer> j = com.google.common.base.Optional.of(null);

        System.out.println(sum(i.get(),j.or(0)));
    }

    public Integer sum(Integer a, Integer b){
        return a + b;
    }
}
