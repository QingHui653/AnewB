package newb.c.a_web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import newb.c.a_spring.api.alipay.util.httpClient.HttpRequest;
import newb.c.a_spring.backend.sql.model.basemodel.User;
import newb.c.a_web.controller.component.service.EsSaveMoviePipeline;
import newb.c.a_web.controller.component.service.MongoDbSaveMoviePipeline;
import newb.c.a_web.controller.component.webmagic.MovieProcessor;
import newb.c.util.ExcelUtil;
import newb.c.util.ExportExcelUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import newb.c.a_web.controller.component.webmagic.GithubRepoPageProcessor;
import newb.c.a_web.controller.component.webmagic.BearProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("webmagic")
public class WebMagicController {
	@Autowired
	private GithubRepoPageProcessor g;
	@Autowired(required=false)
	private MongoDbSaveMoviePipeline mongoDbSaveMoviePipeline;
	@Autowired(required=false)
	private EsSaveMoviePipeline esSaveMoviePipeline;
	@Autowired
	private BearProcessor bearProcessor;
	@Autowired(required=false)
	private MongoTemplate mongoTemplate;

	@RequestMapping(value="getGithub",method=RequestMethod.GET)
	@ApiOperation("测试使用spider爬取github alibaba的项目")
	public void rep() throws Exception {//alibaba  QingHui653
		Spider.create(g)
		.addUrl("https://github.com/alibaba")
//		.addPipeline(new FilePipeline("G:\\movie\\"))
        .addPipeline(new ConsolePipeline())
		.thread(10).run();
	}
	
	/**
	 * 电影爬虫 存至 mongo
	 */
	@RequestMapping(value="getMovieToMongo",method=RequestMethod.GET)
	@ApiOperation("电影爬虫")
	public void getMovie(){
		Spider.create(new MovieProcessor())
//        .addUrl("http://www.80s.tw/movie/list/-2013---")
        .addUrl("http://www.80s.tw/movie/list/")
//        .addPipeline(new FilePipeline("G:\\movie\\"))
        .addPipeline(new ConsolePipeline())
        .addPipeline(mongoDbSaveMoviePipeline)
        .thread(50)
        .run();
	}

	@RequestMapping(value="getBearMovie",method=RequestMethod.GET)
	public void getBearMovie(){
		Spider.create(bearProcessor)
				.addUrl("http://98.126.159.33/V1/Product/detail?pid=1&")
				.addPipeline(new ConsolePipeline())
				.thread(5)
				.run();
		System.out.println("****************************************************");
	}

	@RequestMapping(value="getBearMovieExcel",method=RequestMethod.GET)
	public void getBearMovieExcel(HttpServletResponse response) throws IOException {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("data.baidu_link").is(null));
//		mongoTemplate.remove(query, JSONObject.class);
//		List<JSONObject> jsonObjectList = mongoTemplate.find(query, JSONObject.class);
		List<JSONObject> jsonObjectList = mongoTemplate.findAll(JSONObject.class);
		System.out.println("size  "+jsonObjectList.size());
		ExportExcelUtil excelUtil = new ExportExcelUtil();
		String[] headList ={"片名","地址","大小"};
		excelUtil.simpleExport(response,headList,swap(jsonObjectList));
	}

	private List<String[]> swap(List<JSONObject> jsonObjectList){
		List<String[]> list = new ArrayList();
		for (JSONObject jsonObject : jsonObjectList) {
			String[] strings =new String[3];
			JSONObject data = jsonObject.getJSONObject("data");
			JSONArray tags= data.getJSONArray("tags");
			strings[0]=tags.toJSONString();
			strings[1]=data.getString("baidu_link");
			strings[2]=data.getString("filesize");
			list.add(strings);
		}
		return list;
	}

	/**
	 * 电影爬虫 存至es
	 */
	@RequestMapping(value="getMovieToES",method=RequestMethod.GET)
	@ApiOperation("电影爬虫")
	public void getMovieToES(){
		Spider.create(new MovieProcessor())
				.addUrl("http://www.80s.tw/movie/list/")
				.addPipeline(new ConsolePipeline())
				.addPipeline(esSaveMoviePipeline)
				.thread(50)
				.run();
	}
}
