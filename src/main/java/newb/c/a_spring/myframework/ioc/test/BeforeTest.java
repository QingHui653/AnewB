package newb.c.a_spring.myframework.ioc.test;

import newb.c.a_spring.myframework.ioc.aop.bean.BeforeAdvice;

public class BeforeTest implements BeforeAdvice {
    @Override
    public void before() {
        System.out.println("before~~~~~");
    }
}
