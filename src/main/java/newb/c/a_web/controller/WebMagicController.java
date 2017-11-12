package newb.c.a_web.controller;

import newb.c.a_web.controller.component.service.EsSaveMoviePipeline;
import newb.c.a_web.controller.component.service.MongoDbSaveMoviePipeline;
import newb.c.a_web.controller.component.webmagic.MovieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import newb.c.a_web.controller.component.webmagic.GithubRepoPageProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@Controller
@RequestMapping("webmagic")
public class WebMagicController {
	@Autowired
	private GithubRepoPageProcessor g;
	@Autowired(required=false)
	private MongoDbSaveMoviePipeline mongoDbSaveMoviePipeline;
	@Autowired(required=false)
	private EsSaveMoviePipeline esSaveMoviePipeline;

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
