package newb.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import newb.c.controller.component.service.MongoDbSaveMoviePipeline;
import newb.c.controller.component.webmagic.GithubRepoPageProcessor;
import newb.c.controller.component.webmagic.MovieProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;

@Controller
@RequestMapping("webmagic")
public class WebMagicController {
	@Autowired
	private GithubRepoPageProcessor g;
	@Autowired
	private MongoDbSaveMoviePipeline mongoDbSaveMoviePipeline;
	
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
	 * 电影爬虫
	 * @param response
	 */
	@RequestMapping(value="getMovie",method=RequestMethod.GET)
	@ApiOperation("电影爬虫")
	public void getMovie(){
		Spider.create(new MovieProcessor())
        .addUrl("http://www.80s.tw/movie/list/-2013---")
//        .addPipeline(new FilePipeline("G:\\movie\\"))
        .addPipeline(new ConsolePipeline())
        .addPipeline(mongoDbSaveMoviePipeline)
        .thread(10)
        .run();
	}
}
