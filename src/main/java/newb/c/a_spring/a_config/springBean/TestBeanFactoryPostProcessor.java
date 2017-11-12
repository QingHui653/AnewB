package newb.c.a_spring.a_config.springBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 使用@bean 不知道为什么不起作用 现在配置在xml中
 * BeanFactoryPostProcessor 是BeanFactory的钩子允许
 * 在实例化前
 * 修改bean 的元数据
 * 会拦截所有的bean 需要继续判断bean
 */
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static Logger logger = LoggerFactory.getLogger(TestBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //BeanFactoryPostProcessor可以修改BEAN的配置信息而BeanPostProcessor不能
        //我们在这里修改postProcessorBean的username注入属性
        BeanDefinition bd = beanFactory.getBeanDefinition("processBean");
        MutablePropertyValues pv =  bd.getPropertyValues();
        if(pv.contains("username")) {
            pv.addPropertyValue("username", "2");
            System.out.println("testBeanFactoryPostProcessor修改 bean 元数据 ");
        }
    }
}
