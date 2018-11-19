package newb.c.a_spring.a_config.springBean;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 如果Bean想知道在BeanFactory中设置的名字时可以实现该接口
 */
public class TestBeanNameAware implements BeanNameAware {
    private String beanName;

    /**
     * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("******************************setBeanName(" + name + ")");
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