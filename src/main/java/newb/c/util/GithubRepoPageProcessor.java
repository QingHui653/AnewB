package newb.c.util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://www.oschina\\.net/news/\\w+/)").all());
        // 部分二：定义如何抽取页面信息，并保存下来
    	//http:www.oschina.net/
//        page.putField("author", page.getUrl().regex("https://www\\.oschina\\.net/news/(\\w+)/.*").toString());
//        page.putField("title", page.getHtml().xpath("//div[@class='TodayNews']/ul/li/a/text()").toString());
    	page.putField("title", page.getHtml().xpath("//div[@class='w1000px layout-content g_clr']/ul/li/a/text()").toString());
        if (page.getResultItems().get("title")==null){
            //skip this page
            page.setSkip(true);
        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        System.out.println("------------------------------");
        System.out.println(page.getResultItems().getAll());
        System.out.println("------------------------------");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//      Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.oschina.net/news/*").thread(5).run();
      Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.hao123.com").thread(5).run();
  }
}
