package newb.c.myframework.ioc.test;

import newb.c.myframework.ioc.BeanFactory;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("newb.c.myframework.ioc.BeanFactory");

        Classes classes= (Classes) BeanFactory.getBean("classes");
        Teacher teacher= (Teacher) BeanFactory.getBean("teacher");
        Student student= (Student) BeanFactory.getBean("student");

        classes.showClasses();
//        student.showName();
//        classes.test();
//        student.show();


        /*Field[] fields=classes.getClass().getDeclaredFields();
        for(Field field:fields) {
            System.out.println(field.getName());
        }*/
    }
}
