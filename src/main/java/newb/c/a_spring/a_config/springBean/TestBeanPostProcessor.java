package newb.c.a_spring.a_config.springBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor 是BeanFactory的钩子允许客户对
 * 实例化的bean 在初始化前 进行 修改 （
 * 1 bean实现了InitializingBean接口，对应的方法为afterPropertiesSet
 * 2 在bean定义的时候，通过init-method设置的方法
 * ）
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
        //在 最先调用 多个BeanPostProcessor 好像会覆盖
        logger.info("1.---bean初始化之前调用：bean=" + bean + ", beanName" + beanName);
        if(bean instanceof ProcessBean) {
            ProcessBean pb = (ProcessBean)bean;
            System.out.println("username:"+pb.getUsername()); //2 被beanFactory修改
            System.out.println("password:"+pb.getPassword()); //2
            pb.setUsername("3");
            pb.setPassword("3");
            System.out.println("username:"+pb.getUsername());
            System.out.println("password:"+pb.getPassword());
        }
        return bean;
    }

    /**
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        //在 最后调用 多个BeanPostProcessor 好像会覆盖
        logger.info("5.---bean初始化之后调用：bean=" + bean + ", beanName" + beanName);
        if(bean instanceof ProcessBean) {
            ProcessBean pb = (ProcessBean)bean;
            System.out.println("username:"+pb.getUsername());
            System.out.println("password:"+pb.getPassword());
        }
        return bean;
    }

}
