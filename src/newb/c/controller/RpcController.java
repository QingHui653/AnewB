package newb.c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("rpc")
public class RpcController {
	//dubbo IOC
	/*@Autowired
	private DemoService0 demoService;*/
	
	@RequestMapping(value="/dubbo",method=RequestMethod.GET)
	@ApiOperation("测试dubbo远程调用")
	public Object getDubboServer(){
		/*String hello = (String) demoService.sayHello("tom");  
        System.out.println(hello);   
        List<User> list = (List<User>) demoService.getUsers();*/
		//dubbo test
//        Object hello =demoService.sayHello("tom");  
//        System.out.println(hello);   
//        Object list =demoService.getUsers();
		return null;
	}
}
