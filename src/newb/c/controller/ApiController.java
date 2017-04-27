package newb.c.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
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
import org.springframework.data.redis.core.HashOperations;
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

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.entity.Example;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import newb.c.api.mail.EmailSendManager;
import newb.c.api.mail.SimpleEmail;
import newb.c.api.weather.weather;
import newb.c.controller.component.GithubRepoPageProcessor;
import newb.c.controller.component.MovieProcessor;
import newb.c.controller.component.ProducerServiceImpl;
import newb.c.controller.component.service.MongoDbSaveMoviePipeline;
import newb.c.controller.component.service.MysqlSaveMoviePipeline;
import newb.c.dubbo.DemoService0;
import newb.c.qrcode.MatrixToImageWriter;
import newb.c.backend.model.RepList;
import newb.c.backend.model.basemodel.Movie;
import newb.c.backend.model.basemodel.Result;
import newb.c.backend.model.basemodel.TOrder;
import newb.c.backend.model.basemodel.User;
import newb.c.backend.service.MovieService;
import newb.c.backend.service.ResultService;
import newb.c.backend.service.TOrderService;
import newb.c.backend.service.UserService;
import newb.c.util.authCode.Captcha;
import newb.c.util.authCode.GifCaptcha;
import newb.c.util.authCode.SpecCaptcha;
import newb.c.utilDb.DataHandle;


@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	private ResultService resultService;
	@Autowired 
	private UserService userService;
	@Autowired
	private GithubRepoPageProcessor g;
	@Autowired
	private TOrderService tOrderService;
	@Autowired
	private MongoDbSaveMoviePipeline mongoDbSaveMoviePipeline;
	@Resource(name = "simpleEmailSendManagerImpl")
	private EmailSendManager emailSendManager;
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
	@ApiOperation("测试使用POI获取excel文档")
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
	
	@RequestMapping(value="/getrep",method=RequestMethod.GET)
	@ApiOperation("测试使用通用mapper,自定义通用接口删除数据,重定向到bootstarap")
	public String getrep() {
		resultService.CommonDelMapper("50");
		return "forward:/web/bootstrap.jsp";
	}
	
	@RequestMapping(value="/getrep2",method=RequestMethod.GET)
	@ApiOperation("测试使用通用mapper获取表值,重定向到bootstarap")
	public Object getrep2(ModelMap modelMap) {
		Example example = new Example(Result.class);
		example.createCriteria().andEqualTo("f1", "QingHui653").andCondition("oid>50");
		List<Result>  r =resultService.selectByExample(example);
		modelMap.addAttribute("resultList", r);
		Result r1= resultService.selectByKey(53);
		modelMap.addAttribute("result", r1);
		return "forward:/web/bootstrap.jsp";
	}
	
	@RequestMapping(value="/sendmq",method=RequestMethod.GET)
	@ApiOperation("测试mq发送消息")
	@ResponseBody
	public String sendmq() {
		//ActiveMq
//		producerServiceImpl.sendMessage(destination, "HHHHHworld");
		return "OK";
	}
	
	@RequestMapping(value="/rep/getRes",method=RequestMethod.GET)
	@ApiOperation("获取爬虫爬取信息")
	public String getRepRes(ModelMap modelMap) {
		List<RepList> repList= resultService.getRepList();
		modelMap.addAttribute("repList", repList);
		return "rep/newb";
	}
	
	@RequestMapping(value="/rep/{group}",method=RequestMethod.GET)
	@ApiOperation("通过公司名获取爬虫爬取信息")
	public String getGroupRes(ModelMap modelMap,@PathVariable String group) {
		Example  example =new Example(Result.class);
		example.createCriteria().andEqualTo("f1", group);
		List<Result> GroupList = resultService.selectByExample(example);
		modelMap.addAttribute("GroupList", GroupList);
		return "rep/GroupList";
	}
	
	@RequestMapping(value="/rep/{group}/{project}",method=RequestMethod.GET)
	@ApiOperation("通过公司名和工程名，获取爬虫爬取信息")
	public String getProject(ModelMap modelMap,@PathVariable String group,@PathVariable String project) {
		Example  example =new Example(Result.class);
		example.createCriteria().andEqualTo("f1", group).andEqualTo("f4", project);
		List<Result> GroupList = resultService.selectByExample(example);
		modelMap.addAttribute("GroupList", GroupList);
		return "rep/GroupList";
	}
	
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
	
	@ApiOperation(value="根据城市获取天气情况")
	@RequestMapping(value="/weather/{cityname}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryWeather(@PathVariable String cityname){
		String res= weather.queryWeather(cityname);
		return res;
	}
	
	@RequestMapping(value="/sharding",method=RequestMethod.GET)
	@ApiOperation("sharding-jdbc测试方法")
	@ResponseBody
	public Object shardingTest() {
		TOrder tO0=new TOrder(0, 2, "第0条数据");
		int bool0= tOrderService.insertByXML(tO0);
		TOrder tO1=new TOrder(1, 3, "第1条数据");
		int bool1= tOrderService.insertByXML(tO1);
		TOrder tO2=new TOrder(2, 4, "第2条数据");
		int bool2= tOrderService.insertByXML(tO2);
		
		/*TOrder tO1=new TOrder(1, 1, "第1条数据");
		int bool1= tOrderService.insertByMapper(tO1);
		TOrder tO2=new TOrder(2, 2, "第2条数据");
		int bool2= tOrderService.insertByComm(tO2); */
		/**
		 * 分页插件不支持主键自增,SELECT LAST_INSERT_ID();配置在model中
		 */
		/*User u = new User(10, "xx", "cc");
		int bool0=userService.save(u);*/
		
		return bool0;
	}
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method=RequestMethod.GET)
	@ApiOperation("获取验证码 gif版")
	public void getGifCode(HttpServletResponse response,HttpServletRequest request,HttpSession session){
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
	        //存入Session
	        session = request.getSession(true);
	        System.out.println("hhh 1"+captcha);
	        System.out.println("hhh 2"+captcha.text());
	        System.out.println("hhh 3"+captcha.text().toLowerCase());
	        session.setAttribute("_code",captcha.text().toLowerCase());  
	        //输出
	        captcha.out(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取验证码异常：%s",e.getMessage());
		}
	}
	
	/**
	 * 获取验证码（jpg版本）
	 * @param response
	 */
	@RequestMapping(value="getJPGCode",method=RequestMethod.GET)
	@ApiOperation("获取验证码 jpg版")
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
			HttpSession session = request.getSession(true);  
			//存入Session
			session.setAttribute("_code",captcha.text().toLowerCase());  
			//输出
			captcha.out(response.getOutputStream());
		} catch (Exception e) {
			logger.error("获取验证码异常：%s",e.toString());
		}
	}
	

	@RequestMapping(value="/rep",method=RequestMethod.GET)
	@ApiOperation("测试使用spider爬取github alibaba的项目")
	public void rep() throws Exception {//alibaba  QingHui653
		Spider.create(g)
		.addUrl("https://github.com/alibaba")
		.addPipeline(new FilePipeline("G:\\movie\\"))
        .addPipeline(new ConsolePipeline())
		.thread(10).run();
	}
	
	/**
	 * 电影爬虫
	 * @param response
	 */
	@RequestMapping(value="getMovie",method=RequestMethod.GET)
	@ApiOperation("电影爬虫")
	public void getMovie(){
		Spider.create(new MovieProcessor())
        .addUrl("http://www.80s.tw/movie/list/-2014---")
//        .addPipeline(new FilePipeline("G:\\movie\\"))
        .addPipeline(new ConsolePipeline())
        .addPipeline(mongoDbSaveMoviePipeline)
        .thread(10)
        .run();
	}
	
	
	/**
	 * 发送简单邮件
	 * @throws MessagingException
	 */
	@RequestMapping(value="sendSimpleEmail",method=RequestMethod.GET)
	@ApiOperation("发送 邮件")
	public void sendSimpleEmail() throws MessagingException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSubject("测试在项目中发送邮件");
		Set<String> receivers = new HashSet<>();
		receivers.add("910944453@qq.com");
		simpleEmail.setToSet(receivers);
		simpleEmail.setHtml(false);
		simpleEmail.setContent("wowowowo擦，别被网易屏蔽");
		simpleEmail.setAttachment(false);

		emailSendManager.sendEmail(simpleEmail);
		
		System.out.println("       发送简单邮件成功");
	}
	
	@RequestMapping(value="throwEx",method=RequestMethod.GET)
	@ApiOperation("测试 抛出异常")
	public void throwEx() throws Exception {
		switch(1) {  
        case 1:  
            throw new Exception("浙江温州，浙江温州，浙江温州。最大皮革厂倒闭了");  
        default:
        	throw new Exception("22222222222");  
        }
	}
	
	@RequestMapping(value="shardDatasource",method=RequestMethod.GET)
	@ApiOperation("测试分库  为分布式事务先做 测试")
	public Object shardDatasource() throws Exception {
		TOrder t2= new TOrder(1,2 , "進入newb t_order_1"); //偶数进newb t_order_1
		TOrder t1= new TOrder(2,1 , "進入newb2 t_order_0"); //奇数进newb2 t_order_0
		tOrderService.save(t1);
		
		tOrderService.save(t2);
		
		return "测试分库表完成";
	}
	
	/**
	 * 未加分布式事务配置，事务暂时不可用
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="shardDatasourceTestJTA",method=RequestMethod.GET)
	@ApiOperation("测试分库  为分布式事务先做 测试")
	public Object shardDatasourceTestJTA() throws Exception {
		tOrderService.testJTA();
		
		return "测试分布式 事务";
	}
	
	/**
	 * 同一个库的事务是可用的
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="TestOneData",method=RequestMethod.GET)
	@ApiOperation("测试一个库下的事务情况")
	public Object TestOneData() throws Exception {
		tOrderService.testOneData();
		
		return "一个库下的事务情况";
	}
	
	@RequestMapping(value="ZxingTest",method=RequestMethod.GET)
	@ApiOperation("测试二维码")
	public void zxingTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();

			String content = "这是测试xing二维码生成";
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object> ();
			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix;
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
			// 生成二维码
			MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
			
			out.close();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
