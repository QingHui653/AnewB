package newb.c.webmodule.config.springBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * BeanPostProcessor 是BeanFactory的钩子允许客户对新建的Bean进行修改
 *
 * 会拦截所有的bean 需要继续判断bean
 */
public class TestBeanPostProcessor implements BeanPostProcessor {

    private static Logger logger = LoggerFactory.getLogger(TestBeanPostProcessor.class);

    /**
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        logger.info("bean初始化之前调用：bean=" + bean + ", beanName" + beanName);
        return bean;
    }

    /**
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        logger.info("bean初始化之后调用：bean=" + bean + ", beanName" + beanName);
        return bean;
    }

}
