package newb.c.controller.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import newb.c.model.Result;
import newb.c.service.ResultService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class GithubRepoPageProcessor implements PageProcessor {
	
	@Autowired
	private ResultService resultService;
	
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) { //(https://github\\.com/\\w+/\\w+)
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/QingHui653/\\w+)").all());
        Result r = new Result();
        r.setF1(page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        r.setF2(page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        r.setF3(page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
        page.putField("result", r);
//        JdbcTemplate jdbcTemplate=new JdbcTemplate();
//        jdbcTemplate.execute("create table temp(id int primary key,name varchar(32))");
        resultService.save(r);
//        System.out.println(page.getResultItems().getAll());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())//https://github.com/QingHui653
        .addUrl("https://github.com/QingHui653")//https://github.com/code4craft
        .addPipeline(new FilePipeline("G:\\bean\\")).thread(5).run();
//        .addPipeline(new JsonFilePipeline("G:\\CM\\")).thread(5).run();
//        .addPipeline(new resultPipe()).thread(5).run();
        System.out.println("****************************************************");
    }
}
