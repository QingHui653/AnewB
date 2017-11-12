package newb.c.a_spring.myframework.ioc.test;

import newb.c.a_spring.myframework.ioc.aop.bean.SurroundAdvice;

public class SurroundTest implements SurroundAdvice {
    @Override
    public void before() {
        System.out.println("surround  Before~~~~");
    }

    @Override
    public void after() {
        System.out.println("surround  After~~~~");
    }
}
