package newb.c.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HandlerMappingTest {

	@RequestMapping(value="testHand",method={RequestMethod.GET})
	@ResponseBody
	public Object test() {
		return "这是个test，用来测试handmapping的加载顺序";
	}
}
