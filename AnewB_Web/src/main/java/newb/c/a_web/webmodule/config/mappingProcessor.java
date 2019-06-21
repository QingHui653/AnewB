package newb.c.a_web.webmodule.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class mappingProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization (Object bean, String beanName)
            throws BeansException {
        if(AnnotatedElementUtils.hasAnnotation(bean.getClass(), Controller.class) ){
            if(AnnotatedElementUtils.hasAnnotation(bean.getClass(), RequestMapping.class)){
                RequestMapping requestMapping = bean.getClass().getAnnotation(RequestMapping.class);
                String[] values = requestMapping.value();
                List<String> afterValues =new ArrayList<>();
                for (String value : values) {
                    String afterValue=""+value;
                    afterValues.add(afterValue);
                }
                try{
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(requestMapping);
                    Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                    declaredField.setAccessible(true);
                    Map memberValues = (Map) declaredField.get(invocationHandler);
                    memberValues.put("value", afterValues.toArray(new String[]{}));
                    values = requestMapping.value();
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

}
