package newb.c.a_web.controller;


import lombok.extern.slf4j.Slf4j;
import newb.c.a_spring.backend.sql.service.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("resources/cache")
@Slf4j
public class TestCacheController {
	@Autowired
	TestCacheService testCacheService;
	
	@RequestMapping(value="/def",method=RequestMethod.GET)
    public String testDefault(ModelMap modelMap) throws Exception {
		log.info("def");
		String str = testCacheService.defaultCache("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping(value="/g60s",method=RequestMethod.GET)
    public String testGuavaCache60seconds(ModelMap modelMap) throws Exception {
		log.info("g60s");
		String str = testCacheService.guavaCache60seconds("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping(value="/g10m",method=RequestMethod.GET)
    public ModelAndView testGuavaCache10minutes(ModelMap modelMap) throws Exception {
		log.info("g10");
		String str = testCacheService.guavaCache10minutes("newb");
		return new ModelAndView("index").addObject("test",str);
	}
	@RequestMapping(value="/g1h",method=RequestMethod.GET)
    public String testGuavaCache1hour(ModelMap modelMap) throws Exception {
		log.info("g1");
		String str = testCacheService.guavaCache1hour("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping(value="/r60s",method=RequestMethod.GET)
    public String testRedisCache60seconds(ModelMap modelMap) throws Exception {
		log.info("r60s");
		String str = testCacheService.redisCache60seconds("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping(value="/r10m",method=RequestMethod.GET)
    public String testRedisCache10minutes(ModelMap modelMap) throws Exception {
		log.info("r10m");
		String str = testCacheService.redisCache10minutes("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping(value="/r1h",method=RequestMethod.GET)
    public String testRedisCache1hour(ModelMap modelMap) throws Exception {
		log.info("r1h");
		String str = testCacheService.redisCache1hour("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}

}
