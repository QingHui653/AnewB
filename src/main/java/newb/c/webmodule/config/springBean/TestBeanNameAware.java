package newb.c.webmodule.config.springBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

/**
 * 如果Bean想知道在BeanFactory中设置的名字时可以实现该接口
 */
public class TestBeanNameAware implements BeanNameAware {

    private static Logger logger = LoggerFactory.getLogger(TestBeanNameAware.class);

    private String beanName;

    /**
     * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
     */
    @Override
    public void setBeanName(String name) {
        logger.info("******************************setBeanName(" + name + ")");
        beanName = name;
    }

    /**
     * Getter method for property <tt>beanName</tt>.
     * @return property value of beanName
     */
    public String getBeanName() {
        return beanName;
    }

}