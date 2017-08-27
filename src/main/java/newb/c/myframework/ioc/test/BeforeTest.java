package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.aop.bean.BeforeAdvice;

public class BeforeTest implements BeforeAdvice {
    @Override
    public void before() {
        System.out.println("before~~~~~");
    }
}
