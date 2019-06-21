/**
 * @author woshizbh
 *
 */
package newb.c.a_web.webmodule.webservice.cxf.test;

import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import newb.c.a_web.webmodule.webservice.cxf.HelloService;

public class test{
	/**
	 * 3.还有一种需要进入到jar包bin的目录下运行命令
	 * 会生成client，server类，直接调用即可
	 * 4.配置在spring文件中，直接从spring容器中注入即可
	 */
	
//	@Test
	public void staticService() {
		// 创建WebService客户端代理工厂
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // 判断是否抛出异常
        factory.getOutInterceptors().add(new LoggingInInterceptor());
        // 注册webservice接口
        factory.setServiceClass(HelloService.class);
        // 配置webservice地址
        factory.setAddress("http://127.0.0.1:8083/AnewB/webservice/helloWorld?wsdl");
        // 获得接口对象
        HelloService service = (HelloService) factory.create();
        // 调用接口方法
        String result = service.sayHello("aaaaaaaaaa");
        System.out.println("调用结果:" + result);
        // 关闭接口连接
        System.exit(0);
	}
	
	/**
	 * 动态调用无需提供service，但需要知道
	 *  访问base+/webservice/
	 *  调用service的：url和namespace和调用方法
	 */
//	@Test
	public void dynamicService() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		// url为调用webService的wsdl地址
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://127.0.0.1:8083/AnewB/webservice/helloWorld?wsdl");
        // namespace是命名空间，methodName是方法名
        QName name = new QName("http://cxf.webservice.webmodule.c.newb/", "sayHello");
        // paramvalue为参数值
        String xmlStr = "aaaaaaaa";
        Object[] objects;
        try {
            objects = client.invoke(name, xmlStr);
            System.out.println(objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	@Test
	public void dynamicService2() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://127.0.0.1:8083/AnewB/webservice/helloWorld?wsdl");
        String xmlStr = "aaaaaaaa";
        Object[] objects;
        try {
            objects = client.invoke("sayHello", xmlStr);
            System.out.println(objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}