package test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        System.out.println("------------------------------");
        System.out.println("-----------开始-----------");
        System.out.println("------------------------------");
        System.out.println(page.getResultItems().getAll().get("author"));
        System.out.println("-----------内容-----------");
        System.out.println("------------------------------");
//        System.out.println(page.getResultItems().get("readme").toString());
        System.out.println("-----------结束---------------");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())//https://github.com/QingHui653
        .addUrl("https://github.com/QingHui653")//https://github.com/code4craft
        .addPipeline(new JsonFilePipeline("G:\\bean\\")).thread(5).run();
    }
}
