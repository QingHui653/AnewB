package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.annotation.MyAutowired;
import newb.c.myframework.ioc.annotation.MyBean;
import newb.c.myframework.ioc.aop.annotation.PointCut;

/**
 * 班级类
 */
@MyBean("classes")
@PointCut("newb.c.myframework.ioc.test.AfterTest")
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

    @PointCut("newb.c.myframework.ioc.test.BeforeTest")
    public void test() {
        System.out.println("做一下");
    }
}
