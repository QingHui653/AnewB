package newb.c.a_spring.myframework.ioc.test;

import newb.c.a_spring.myframework.ioc.annotation.MyAutowired;
import newb.c.a_spring.myframework.ioc.annotation.MyBean;
import newb.c.a_spring.myframework.ioc.aop.annotation.PointCut;

/**
 * 班级类
 */
@MyBean("classes")
@PointCut("AfterTest")
public class Classes {

    @MyAutowired
    private Teacher teacher;
    private String classname;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void showClasses() {
        teacher.setName("苍老师");
        classname="计算机班";
        System.out.println(classname);
        System.out.println(teacher);
    }

    @PointCut("BeforeTest")
    public void test() {
        System.out.println("做一下");
    }
}
