package newb.c.webmodule.config.springBean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SpringConfig {
    /**
     * 测试spring初始化bean
     * 具体可查看 360 问题 spring加载bean
     * @return TestBean
     */
    @Bean(initMethod = "init")
    public TestInitializingBean testInitializingBean() {
        return new TestInitializingBean();
    }

    @Bean
    public TestApplicationContextAware testApplicationContextAware(){
        return new TestApplicationContextAware();
    }

    @Bean(name = "beanNameAware")
    public TestBeanNameAware testBeanNameAware(){
        return new TestBeanNameAware();
    }

//    @Bean
    public TestBeanPostProcessor testBeanPostProcessor(){
        return new TestBeanPostProcessor();
    }
}
