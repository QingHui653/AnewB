package newb.c.myframework.rpc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import newb.c.myframework.rpc.bean.RpcCommand;
import newb.c.myframework.rpc.bean.RpcResponse;

public class RpcHandler {
	
	ConcurrentHashMap<String, Object> registered =new ConcurrentHashMap<>(128);
	
	/**
	 * 出来请求对象
	 * @param commond
	 * @return
	 */
	
	public RpcResponse handler (RpcCommand commond){
		
		String className =commond.getClassName();
		RpcResponse response =new RpcResponse();
		
		try {
			Object obj =registered.get(className);
			String[] argTypes = commond.getArgumetsType();
			Class aClass =Class.forName(className);
			
			List<Class> argsTypeList =new ArrayList<>(argTypes.length);
			for (String s : argTypes) {
				argsTypeList.add(Class.forName(s));
			}
			System.out.println("调用的方法为 "+commond.getMethodName());
			Method method =aClass.getMethod(commond.getMethodName(),argsTypeList.toArray(new Class[argsTypeList.size()]));
			Object object =method.invoke(obj, commond.getParams());
			
			response.setResult(object);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public void regist(Class interfa , Object object) {
		registered.put(interfa.getName(), object);
	}
}
