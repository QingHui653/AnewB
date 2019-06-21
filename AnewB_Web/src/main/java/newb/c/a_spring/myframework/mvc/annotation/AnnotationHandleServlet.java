package newb.c.a_spring.myframework.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newb.c.a_spring.myframework.mvc.bean.DispatchActionConstant;
import newb.c.a_spring.myframework.mvc.bean.RequestMapingMap;
import newb.c.a_spring.myframework.mvc.bean.View;
import newb.c.a_spring.myframework.mvc.bean.WebContext;
import newb.c.a_spring.myframework.mvc.until.BeanUtils;
import newb.c.a_spring.myframework.mvc.until.ScanClassUtil;

/**
 * <p>ClassName: AnnotationHandleServlet<p>
 * <p>Description: AnnotationHandleServlet作为自定义注解的核心处理器以及负责调用目标业务方法处理用户请求<p>
 */
public class AnnotationHandleServlet extends HttpServlet {

    private String pareRequestURI(HttpServletRequest request){
        String path = request.getContextPath()+"/";
        String requestUri = request.getRequestURI();
        String midUrl = requestUri.replaceFirst(path, "");
        String lasturl = midUrl.substring(0, midUrl.lastIndexOf("."));
        return lasturl;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        /**
         * 重写了Servlet的init方法后一定要记得调用父类的init方法，
         * 否则在service/doGet/doPost方法中使用getServletContext()方法获取ServletContext对象时
         * 就会出现java.lang.NullPointerException异常
         */
        super.init(config);
        System.out.println("---初始化开始start---");
        //获取web.xml中配置的要扫描的包
        String basePackage = config.getInitParameter("basePackage");
        //如果配置了多个包，例如：<param-value>me.gacl.web.controller,me.gacl.web.UI</param-value>
        if (basePackage.indexOf(",")>0) {
            //按逗号进行分隔
            String[] packageNameArr = basePackage.split(",");
            for (String packageName : packageNameArr) {
                initRequestMapingMap(packageName);
            }
        }else {
            initRequestMapingMap(basePackage);
        }
        System.out.println("----初始化结束end---");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.excute(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.excute(request, response);
    }

    public void Service(HttpServletRequest req, HttpServletResponse resp){
    	/**
    	 * 流程1.进入init，init在运行tomcat是运行一次，且仅运行一次，
    	 * 2.进入service，service根据http协议，分别进入doget，dopost
    	 * 3.destroy,tomcat停止时运行一次
    	 */
    	System.out.println("进入service方法");
    }
    @Override
    public void destroy() {
    	System.out.println("进入destroy方法");
    }

    private void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //将当前线程中HttpServletRequest对象存储到ThreadLocal中，以便在Controller类中使用
        WebContext.requestHodler.set(request);
        //将当前线程中HttpServletResponse对象存储到ThreadLocal中，以便在Controller类中使用
        WebContext.responseHodler.set(response);
        //解析url
        String lasturl = pareRequestURI(request);
        //获取要使用的类
        Class<?> clazz = RequestMapingMap.getRequesetMap().get(lasturl);
        //创建类的实例
        Object classInstance = BeanUtils.instanceClass(clazz);
        //获取类中定义的方法
        Method [] methods = BeanUtils.findDeclaredMethods(clazz);
        Method method = null;
        for(Method m:methods){//循环方法，找匹配的方法进行执行
            if(m.isAnnotationPresent(MyRequestMapping.class)){
                String anoPath = m.getAnnotation(MyRequestMapping.class).value();
                if(anoPath!=null && !"".equals(anoPath.trim()) && lasturl.equals(anoPath.trim())){
                    //找到要执行的目标方法
                    method = m;
                    break;
                }
            }
        }
        try {
            if(method!=null){
                //执行目标方法处理用户请求
                Object retObject = method.invoke(classInstance);
                //如果方法有返回值，那么就表示用户需要返回视图
                if (retObject!=null) {
                    View view = (View)retObject;
                    //判断要使用的跳转方式
                    if(view.getDispathAction().equals(DispatchActionConstant.FORWARD)){
                        //使用服务器端跳转方式
                        request.getRequestDispatcher(view.getUrl()).forward(request, response);
                    }else if(view.getDispathAction().equals(DispatchActionConstant.REDIRECT)){
                        //使用客户端跳转方式
                        response.sendRedirect(request.getContextPath()+view.getUrl());
                    }else{
                        request.getRequestDispatcher(view.getUrl()).forward(request, response);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
    * @Method: initRequestMapingMap
    * @Description:添加使用了Controller注解的Class到RequestMapingMap中
    */
    private void initRequestMapingMap(String packageName){
        Set<Class<?>> setClasses =  ScanClassUtil.getClasses(packageName);
        for (Class<?> clazz :setClasses) {
            if (clazz.isAnnotationPresent(MyController.class)) {
                Method [] methods = BeanUtils.findDeclaredMethods(clazz);
                for(Method m:methods){//循环方法，找匹配的方法进行执行
                    if(m.isAnnotationPresent(MyRequestMapping.class)){
                        String anoPath = m.getAnnotation(MyRequestMapping.class).value();
                        if(anoPath!=null && !"".equals(anoPath.trim())){
                            if (RequestMapingMap.getRequesetMap().containsKey(anoPath)) {
                                throw new RuntimeException("RequestMapping映射的地址不允许重复！");
                            }
                            RequestMapingMap.put(anoPath, clazz);
                        }
                    }
                }
            }
        }
    }

}
