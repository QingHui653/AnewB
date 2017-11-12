package newb.c.a_spring.myframework.ioc.test;

import newb.c.a_spring.myframework.ioc.annotation.MyBean;
import newb.c.a_spring.myframework.ioc.aop.annotation.PointCut;

@MyBean("teacher")
@PointCut("SurroundTest")
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
