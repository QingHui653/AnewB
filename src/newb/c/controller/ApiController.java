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

import javax.jms.Destination;

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

import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.entity.Example;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import newb.c.controller.component.GithubRepoPageProcessor;
import newb.c.controller.component.ProducerServiceImpl;
import newb.c.model.RepList;
import newb.c.model.Result;
import newb.c.service.ResultService;
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
	private GithubRepoPageProcessor g;
//	@Autowired
//	private ProducerServiceImpl producerServiceImpl;
	/*@Autowired
	private Destination destination; */
	
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
	
	/*@RequestMapping("/sendmq")
	@ResponseBody
	public String sendmq() {
		producerServiceImpl.sendMessage(destination, "HHHHHworld");
		return "OK";
	}*/
	
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
}
