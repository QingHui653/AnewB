package newb.c.a_spring.myframework.ioc.test;

import newb.c.a_spring.myframework.ioc.annotation.MyAutowired;
import newb.c.a_spring.myframework.ioc.annotation.MyBean;
import newb.c.a_spring.myframework.ioc.aop.annotation.PointCut;

@MyBean("student")
@PointCut("BeforeTest")
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
