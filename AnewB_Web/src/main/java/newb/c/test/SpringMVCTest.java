package newb.c.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by woshizbh on 2017/1/12.
 * @param <T>
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:main/resources/springMVC.xml"})*/
public class SpringMVCTest<T> {

    public void multipartResolverTest(){
    	@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "/config/springMVC.xml" });
        context.start();  
  
        CommonsMultipartResolver multipartResolver=context.getBean("multipartResolver", CommonsMultipartResolver.class); //  
        
        System.out.println("OK 上传文件的编码为 "+multipartResolver.getFileUpload().getHeaderEncoding());
        System.out.println("OK 上传文件的size为 "+multipartResolver.getFileUpload().getSizeMax());
        
    }
    
    @Test
    public void handlerMappingsTest(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "/config/springMVC.xml" });
        context.start();  
		Map<String, HandlerMapping> matchingBeans =BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
		
		String[] beanNames = (false ?BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, Object.class) :context.getBeanNamesForType(Object.class));
		
		System.out.println("beanNames "+Arrays.toString(beanNames));
		
		
		for (Entry<String, HandlerMapping> HandlerMapps : matchingBeans.entrySet()) {
			System.out.println("handlerMapper : key "+HandlerMapps.getKey() +" value "+HandlerMapps.getValue());
		}
		
		/*for (String beanName : beanNames) {
			if (!beanName.startsWith("scopedTarget.")) {
				Class<?> beanType = null;
				try {
					beanType = context.getType(beanName);
				}
				catch (Throwable ex) {
						System.out.printf("Could not resolve target class for bean with name '" + beanName + "'", ex);
				}
				if (beanType != null && isHandler(beanType)) {
					//detectHandlerMethods方法
					Class<?> handlerType = (beanName instanceof String ?context.getType((String) beanName) : beanName.getClass());
					final Class<?> userType = ClassUtils.getUserClass(handlerType);
					Map<Method, T> methods = MethodIntrospector.selectMethods(userType,
							new MethodIntrospector.MetadataLookup<T>() {
								@Override
								public T inspect(Method method) {
									try {
										
										return getMappingForMethod(method, userType);
									}
									catch (Throwable ex) {
										throw new IllegalStateException("Invalid mapping on handler class [" +
												userType.getName() + "]: " + method, ex);
									}
								}
							});
					//注册handlerMethod
					for (Map.Entry<Method, T> entry : methods.entrySet()) {
						Method invocableMethod = AopUtils.selectInvocableMethod(entry.getKey(), userType);
						//在这里就获取到mapping的URL地址了
						T mapping = entry.getValue();
						registerHandlerMethod(handler, invocableMethod, mapping);
					}
				}
			}
		}*/
		
    }
    
    //判断Controller ,RequestMapping注解
    /*public boolean isHandler(Class<?> beanType) {
    	return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
				AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
	}*/
    
    //注册
    /*protected void registerHandlerMethod(Object handler, Method method, T mapping) {
		this.mappingRegistry.register(mapping, handler, method);
	}*/
    
    /**
     * 获取到mapping 映射的URL
     * @param mapping
     * @param handler
     * @param method
     */
    /*public void register(T mapping, Object handler, Method method) {
		this.readWriteLock.writeLock().lock();
		try {
			HandlerMethod handlerMethod = createHandlerMethod(handler, method);
			assertUniqueMethodMapping(handlerMethod, mapping);

			if (logger.isInfoEnabled()) {
				logger.info("Mapped \"" + mapping + "\" onto " + handlerMethod);
			}
			this.mappingLookup.put(mapping, handlerMethod);

			List<String> directUrls = getDirectUrls(mapping);
			for (String url : directUrls) {
				this.urlLookup.add(url, mapping);
			}

			String name = null;
			if (getNamingStrategy() != null) {
				name = getNamingStrategy().getName(handlerMethod, mapping);
				addMappingName(name, handlerMethod);
			}

			CorsConfiguration corsConfig = initCorsConfiguration(handler, method, mapping);
			if (corsConfig != null) {
				this.corsLookup.put(handlerMethod, corsConfig);
			}

			this.registry.put(mapping, new MappingRegistration<T>(mapping, handlerMethod, directUrls, name));
		}
		finally {
			this.readWriteLock.writeLock().unlock();
		}
	}*/
}
