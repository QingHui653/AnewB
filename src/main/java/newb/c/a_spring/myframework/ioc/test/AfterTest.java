package newb.c.a_spring.myframework.ioc.test;

import newb.c.a_spring.myframework.ioc.aop.bean.AfterAdvice;

public class AfterTest implements AfterAdvice {
    @Override
    public void after() {
        System.out.println("after~~~~~~");
    }
}
