package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.aop.bean.SurroundAdvice;

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
