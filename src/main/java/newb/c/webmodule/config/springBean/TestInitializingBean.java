package newb.c.webmodule.config.springBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 如果期望在BeanFactory 设置所有的属性后作出进一步的反应可以实现该接口
 * 执行顺序是， afterPropertiesSet()->initMethod()(使用init)
 * 如果使用 @PostConstruct 则 为 构造方法 initMethod() afterPropertiesSet()
 */

/**
 * 关于在spring  容器初始化 bean 和销毁前所做的操作定义方式有三种：
 第一种：通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
 第二种是：通过 在xml中定义init-method 和  destory-method方法
 第三种是： 通过bean实现InitializingBean和 DisposableBean接口
 三种顺序为 @PostConstruct ->InitializingBean -> 使用init
 */
public class TestInitializingBean implements InitializingBean {
    String name;

    public String getName() {
        return name;
    }

    public TestInitializingBean() {
        System.out.println("构造方法执行");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() {
        System.out.println("init方法执行");
        System.out.println("******************************");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct方法执行");
        System.out.println("******************************");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("------调用afterPropertiesSet()");
        System.out.println("******************************");
    }
}
