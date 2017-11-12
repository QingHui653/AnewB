package newb.c.a_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/mock")
public class MockController {

	@RequestMapping(value = "mockTest",method = RequestMethod.GET)
	public String mockTest(){
		return  "mock";
	}
}
