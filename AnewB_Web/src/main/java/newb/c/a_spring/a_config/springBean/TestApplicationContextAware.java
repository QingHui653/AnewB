package newb.c.a_spring.a_config.springBean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 任何期望在ApplicationContext运行的时候被通知到都可以实现该接口
 */
public class TestApplicationContextAware implements ApplicationContextAware {
    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("******************************ApplicationContext runs in");
    }

}
