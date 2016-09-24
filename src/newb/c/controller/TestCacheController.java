package newb.c.controller;


import newb.c.service.TestCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("cache")
public class TestCacheController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestCacheController.class);
	
	@Autowired
	TestCacheService testCacheService;
	
	@RequestMapping("/def")
    public String testDefault(ModelMap modelMap) throws Exception {
		logger.info("def");
		String str = testCacheService.defaultCache("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping("/g60s")
    public String testGuavaCache60seconds(ModelMap modelMap) throws Exception {
		logger.info("g60s");
		String str = testCacheService.guavaCache60seconds("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping("/g10m")
    public ModelAndView testGuavaCache10minutes(ModelMap modelMap) throws Exception {
		logger.info("g10");
		String str = testCacheService.guavaCache10minutes("newb");
		return new ModelAndView("index").addObject("test",str);
	}
	@RequestMapping("/g1h")
    public String testGuavaCache1hour(ModelMap modelMap) throws Exception {
		logger.info("g1");
		String str = testCacheService.guavaCache1hour("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping("/r60s")
    public String testRedisCache60seconds(ModelMap modelMap) throws Exception {
		logger.info("r60s");
		String str = testCacheService.redisCache60seconds("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping("/r10m")
    public String testRedisCache10minutes(ModelMap modelMap) throws Exception {
		logger.info("r10m");
		String str = testCacheService.redisCache10minutes("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}
	@RequestMapping("/r1h")
    public String testRedisCache1hour(ModelMap modelMap) throws Exception {
		logger.info("r1h");
		String str = testCacheService.redisCache1hour("newb");
		modelMap.addAttribute("test", str);
		return "testCache";
	}

}
