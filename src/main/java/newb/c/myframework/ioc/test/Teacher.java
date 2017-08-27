package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.annotation.MyBean;
import newb.c.myframework.ioc.aop.annotation.PointCut;

@MyBean("teacher")
@PointCut("newb.c.myframework.ioc.test.SurroundTest")
public class Teacher {
    /*@MyAutowired
    private Student student;*/
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}
