package newb.c.a_web.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("view")
public class ViewController {
	
	@ApiOperation(value="第一个freemark ")
	@GetMapping("helloFreemark")
	public Object helloFreemark() {
		ModelAndView mv = new ModelAndView("views/freemarker/hello");
		
		List<String> list =new ArrayList<>();
			list.add("111");
			list.add("222");
			list.add("333");
		
		mv.addObject("title", "Spring MVC And Freemarker");
		mv.addObject("content", " Hello world ， test my first freemarker ! ");
		mv.addObject("list", list);
		mv.addObject("if", "true");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/velocity", method = RequestMethod.GET)
    public String getTest(Model model) {
        model.addAttribute("hello", "test velocity");
        return "/views/velocity/hello";
    }
}
