package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.annotation.MyAutowired;
import newb.c.myframework.ioc.annotation.MyBean;
import newb.c.myframework.ioc.aop.annotation.PointCut;

@MyBean("student")
@PointCut("newb.c.myframework.ioc.test.BeforeTest")
public class Student {
    @MyAutowired
    private Teacher teacher;
    @MyAutowired
    private String name;

    public void showName() {
        name="hehe";
        System.out.println(name);
    }

//    @PointCut("mySpring.client.SurroundTest")
    public void show() {
        name="嘻嘻";
        System.out.println(name);
        System.out.println(teacher);
    }
}
