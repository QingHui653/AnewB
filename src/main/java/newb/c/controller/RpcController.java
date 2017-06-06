package newb.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import newb.c.dubbo.DemoService;
@Controller
@RequestMapping("rpc")
public class RpcController {
	//dubbo IOC
	@Autowired(required=false)
	@Qualifier(value="demoServiceTest")
	private DemoService demoServiceTest;
	
	@RequestMapping(value="/dubbo",method=RequestMethod.GET)
	@ApiOperation("测试dubbo远程调用")
	public Object getDubboServer(){
		/*String hello = (String) demoService.sayHello("tom");  
        System.out.println(hello);   
        List<User> list = (List<User>) demoService.getUsers();*/
		//dubbo test
        Object hello =demoServiceTest.sayHello("tom");  
        System.out.println(hello);   
        Object list =demoServiceTest.getUsers();
		return list;
	}
}
