package newb.c.controller.component.webmagic;


import org.apache.http.HttpHost;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import newb.c.backend.sql.model.basemodel.Result;
import newb.c.backend.sql.service.ResultService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:main/resources/mybatis/spring-mybatis.xml"})
public class GithubRepoPageProcessor implements PageProcessor {
	
	@Autowired
	private ResultService resultService;
	
	/*@Resource
	private ResultMapper resultMapper;*/
	
	public HttpHost httpHost =new HttpHost("113.117.204.99", 9999);
	private Site site = Site.me().setTimeOut(60000).setRetryTimes(10)//.setHttpProxy(httpHost)
            .setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/");

    @Override
    public void process(Page page) { //(https://github\\.com/\\w+/\\w+)
    	
    	page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    	page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
    	/*String name;
    	if (page.getUrl().toString().contains("?")) {
    		name=page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf("/")+1,page.getUrl().toString().indexOf("?"));
		}else {
			name=page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf("/")+1);
		}*/
//    	page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/"+name+"/\\w+)").all());
//        page.addTargetRequest(page.getHtml().links().regex(""));
//        page.addTargetRequests(page.getHtml().links().regex(regexUrl).all());
        Result r = new Result();
        r.setF1(page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        r.setF2(page.getHtml().xpath("//*[@id='js-repo-pjax-container']/div[1]/div[1]/h1/strong/a/tidyText()").toString());// //h1[@class='entry-title public']/strong/a/text()
        r.setF4(page.getHtml().xpath("//*[@id='js-repo-pjax-container']/div[1]/div[1]/h1/strong/a/text()").toString());// //h1[@class='entry-title public']/strong/a/text()
        r.setF3(page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
        page.putField("result", r);
        if (r.getF3()!=null) {
        		resultService.save(r);
//        	resultMapper.insert(r);
		}
//        System.out.println(page.getResultItems().getAll());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())//https://github.com/QingHui653
        .addUrl("https://github.com/QingHui653")//https://github.com/code4craft
        .addPipeline(new FilePipeline("G:\\bean\\"))
        .thread(10).run();
        System.out.println("****************************************************");
    }
}
