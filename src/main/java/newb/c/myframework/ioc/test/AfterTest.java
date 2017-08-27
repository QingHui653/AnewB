package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.aop.bean.AfterAdvice;

public class AfterTest implements AfterAdvice {
    @Override
    public void after() {
        System.out.println("after~~~~~~");
    }
}
