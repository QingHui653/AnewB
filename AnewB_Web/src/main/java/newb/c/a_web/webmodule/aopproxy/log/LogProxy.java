package newb.c.a_web.webmodule.aopproxy.log;

import java.text.SimpleDateFormat;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class LogProxy {
	private Map<Integer,Integer> trackCounts =new HashMap<>();

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Pointcut("execution(* newb.c.a_web.controller..*Controller.*(..)) ")//execution(* newb.c.controller.*.*(..))
    private void Log() {
    }

    public int getCount(int trackNumber){
	    return trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber):0;
    }

	@Before("Log()")
	public void beforeAdvice(JoinPoint point) throws ClassNotFoundException {
		/*logger.info("");
		logger.info("");
		logger.info("-------------------------------日志代理开始--------------------------------------------------");
		logger.info("----------------进入的方法为 --- "+point.toString());
		logger.info("----------------传入的参数为---" +Arrays.toString(point.getArgs()));
		logger.info("----------------传入的参数为---" +Arrays.toString(point.getArgs()));
		String curtime=sdf.format(Calendar.getInstance().getTime());
		logger.info("----------------开始时间---"+curtime);*/
		
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------------------日志代理开始--------------------------------------------------");
		System.out.println("----------------进入的方法为 --- "+point.toString());
		//Struts2的切面方法切不到,所以只能输出这个
//		System.out.println("----------------截取Struts2 --- "+point.getThis()+"+++"+point.toShortString());
		//Struts2的切面方法切不到,所以只能输出这个
		System.out.println("----------------传入的参数为---" +Arrays.toString(point.getArgs()));
//		System.out.println("1--"+point.toString());
//		System.out.println("2--"+point.toShortString());
//		System.out.println("3--"+point.toLongString());
//		Class<?> c = Class.forName(point.getSignature().getDeclaringTypeName());
//		System.out.println("代理--"+c.getName());
//		Arrays.toString(c.getMethods());
		String curtime=sdf.format(Calendar.getInstance().getTime());
	 	System.out.println("----------------开始时间---"+curtime);
	}

	@After("Log()")
	public void afterAdvice() {
		String curtime=sdf.format(Calendar.getInstance().getTime());
		System.out.println("----------------结束时间---"+curtime);
		System.out.println("-------------------------------日志代理结束--------------------------------------------------");
	}

	/*@Around("Log()")
	public void around(ProceedingJoinPoint jp) {
		try {
			System.out.println("环绕通知前 ---1");
			System.out.println("环绕通知前 ---2");
			jp.proceed();//运行
			System.out.println("环绕通知后 ---3");
		} catch (Throwable throwable) {
			System.out.println("抛出异常");
			throwable.printStackTrace();
		}
	}*/


	@SuppressWarnings("rawtypes")
	@AfterReturning(pointcut = "Log()", returning = "retVal")
	public void afterReturningAdvice(Object retVal) {
		if (retVal!=null) {
			if(retVal instanceof List){
				System.out.println("----------------返回List的个数为--"+((List)retVal).size());
			}else {
				System.out.println("----------------返回值为--" + retVal.toString());
			}
		}
	}

	@AfterThrowing(pointcut = "Log()", throwing = "ex")
	public void AfterThrowingAdvice(IllegalArgumentException ex) {
		System.out.println("日志Aop出错了" + ex.toString());
	}
}
