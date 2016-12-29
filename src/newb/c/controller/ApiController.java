package newb.c.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;














import java.util.Properties;
import java.util.concurrent.Callable;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.entity.Example;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import newb.c.api.weather.weather;
import newb.c.controller.component.GithubRepoPageProcessor;
import newb.c.controller.component.ProducerServiceImpl;
import newb.c.dubbo.DemoService0;
import newb.c.backend.model.RepList;
import newb.c.backend.model.basemodel.Result;
import newb.c.backend.model.basemodel.TOrder;
import newb.c.backend.model.basemodel.User;  
import newb.c.backend.service.ResultService;
import newb.c.backend.service.TOrderService;
import newb.c.backend.service.UserService;
import newb.c.util.authCode.Captcha;
import newb.c.util.authCode.GifCaptcha;
import newb.c.util.authCode.SpecCaptcha;
import newb.c.utilDb.DataHandle;


@Controller
@RequestMapping("api")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"spring*.xml"})
public class ApiController {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ResultService resultService;
	@Autowired 
	private UserService userService;
	@Autowired
	private GithubRepoPageProcessor g;
	@Autowired
	private TOrderService tOrderService;
	//ActiveMQ
	/*@Autowired
	private ProducerServiceImpl producerServiceImpl;
	@Autowired
	private Destination destination;*/
	//dubbo IOC
	/*@Autowired
	private DemoService0 demoService;*/
	
	weather weather = new weather();

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	DataHandle data =new DataHandle();
	
	/**
	 *   获取excel 文档
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	@RequestMapping(value="/getExc",method=RequestMethod.GET)
	@Test   
	public void getExc() throws SQLException,
			IOException, RowsExceededException, WriteException {
		String[] title = {"姓名"};
		String filePath = "g:\\newbc.xls";
		Connection send = DriverManager.getConnection("proxool.local");
		String a0101 = "select username from user";
		List<String> a0101List = data.getDataStringList(a0101, send);
		WritableWorkbook wwb;
		OutputStream out = new FileOutputStream(filePath);
		wwb = Workbook.createWorkbook(out);
		WritableSheet sheet = wwb.createSheet("第一页", 0);
		Label label;
		for (int i = 0; i < title.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			label = new Label(i, 0, title[i]);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		}
		for (int j = 0; j < a0101List.size(); j++) {
			label = new Label(1, j+1, a0101List.get(j).toString());
			sheet.addCell(label);
		}
		logger.info("保存路径为-- "+filePath);
		wwb.write();
		wwb.close();
		logger.info("保存成功");
	}
	
	@RequestMapping(value="/rep",method=RequestMethod.GET)
	public void rep() {//alibaba  QingHui653
		String[] urls =new String[9];
		for (int i = 1; i < 10; i++) {
			String url="https://github.com/alibaba";
			if (i!=1) {
				url=url+"?page="+i;
			}
			urls[i-1]=url;
		}
		System.out.println("--"+Arrays.toString(urls));
		Spider.create(g)
		.addUrl(urls)
		.thread(5).run();
	}
	
	@RequestMapping(value="/getrep",method=RequestMethod.GET)
	public String getrep() {
		resultService.CommonDelMapper("50");
		return "forward:/web/bootstrap.jsp";
	}
	
	@RequestMapping(value="/getrep2",method=RequestMethod.GET)
	public Object getrep2(ModelMap modelMap) {
		Example example = new Example(Result.class);
		example.createCriteria().andEqualTo("f1", "QingHui653").andCondition("oid>50");
		List<Result>  r =resultService.selectByExample(example);
		modelMap.addAttribute("resultList", r);
		Result r1= resultService.selectByKey(53);
		modelMap.addAttribute("result", r1);
		return "forward:/web/bootstrap.jsp";
	}
	
	@RequestMapping(value="/redis/add",method=RequestMethod.GET)
	public void add() {
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		valueOper.set("CC", "测试中文");
		System.out.println("redis 查询"+valueOper.get("CC"));
    }
	
	@RequestMapping(value="/sendmq",method=RequestMethod.GET)
	@ResponseBody
	public String sendmq() {
		//ActiveMq
//		producerServiceImpl.sendMessage(destination, "HHHHHworld");
		return "OK";
	}
	
	@RequestMapping(value="/rep/getRes",method=RequestMethod.GET)
	public String getRepRes(ModelMap modelMap) {
		List<RepList> repList= resultService.getRepList();
		modelMap.addAttribute("repList", repList);
		return "rep/newb";
	}
	
	@RequestMapping(value="/rep/{group}",method=RequestMethod.GET)
	public String getGroupRes(ModelMap modelMap,@PathVariable String group) {
		Example  example =new Example(Result.class);
		example.createCriteria().andEqualTo("f1", group);
		List<Result> GroupList = resultService.selectByExample(example);
		modelMap.addAttribute("GroupList", GroupList);
		return "rep/GroupList";
	}
	
	@RequestMapping(value="/rep/{group}/{project}",method=RequestMethod.GET)
	public String getProject(ModelMap modelMap,@PathVariable String group,@PathVariable String project) {
		Example  example =new Example(Result.class);
		example.createCriteria().andEqualTo("f1", group).andEqualTo("f4", project);
		List<Result> GroupList = resultService.selectByExample(example);
		modelMap.addAttribute("GroupList", GroupList);
		return "rep/GroupList";
	}
	
	@RequestMapping(value="/dubbo",method=RequestMethod.GET)
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
	
	@ApiOperation(value="根据城市获取天气情况")
	@RequestMapping(value="/weather/{cityname}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryWeather(@PathVariable String cityname){
		String res= weather.queryWeather(cityname);
		return res;
	}
	
	@RequestMapping(value="/sharding",method=RequestMethod.GET)
	@ResponseBody
	public Object shardingTest() {
		/*TOrder tO0=new TOrder(0, 0, "第0条数据");
		int bool0= tOrderService.insertByXML(tO0);
		TOrder tO1=new TOrder(1, 1, "第1条数据");
		int bool1= tOrderService.insertByXML(tO1);
		TOrder tO2=new TOrder(2, 2, "第2条数据");
		int bool2= tOrderService.insertByXML(tO2);*/
		
//		TOrder tO1=new TOrder(1, 1, "第1条数据");
//		int bool1= tOrderService.insertByMapper(tO1);
//		TOrder tO2=new TOrder(2, 2, "第2条数据");
//		int bool2= tOrderService.insertByComm(tO2); 
		/**
		 * 分页插件不支持主键自增,SELECT LAST_INSERT_ID();配置在model中
		 */
		User u = new User(10, "xx", "cc");
		int bool0=userService.save(u);
		
		return bool0;
	}
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/gif");  
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        Captcha captcha = new GifCaptcha(146,33,4);
	        //输出
	        captcha.out(response.getOutputStream());
	        HttpSession session = request.getSession(true);  
	        //存入Session
	        session.setAttribute("_code",captcha.text().toLowerCase());  
		} catch (Exception e) {
			logger.error("获取验证码异常：%s",e.getMessage());
		}
	}
	
	/**
	 * 获取验证码（jpg版本）
	 * @param response
	 */
	@RequestMapping(value="getJPGCode",method=RequestMethod.GET)
	public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("image/jpg");  
			/**
			 * jgp格式验证码
			 * 宽，高，位数。
			 */
			Captcha captcha = new SpecCaptcha(146,33,4);
			//输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);  
			//存入Session
			session.setAttribute("_code",captcha.text().toLowerCase());  
		} catch (Exception e) {
			logger.error("获取验证码异常：%s",e.getMessage());
		}
	}
}
