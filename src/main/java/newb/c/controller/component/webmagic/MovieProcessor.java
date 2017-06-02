package newb.c.controller.component.webmagic;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import newb.c.backend.model.basemodel.Movie;
import newb.c.backend.service.MovieService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class MovieProcessor implements PageProcessor {
	
	private Site site = Site.me().setTimeOut(30000).setRetryTimes(3)
            .setSleepTime(1000).setCharset("UTF-8");

    @Override
    public void process(Page page) {
        String url =  page.getUrl().toString();
        Pattern pattern1 = Pattern.compile("http://www.80s.tw/movie/list/-(\\d*)+---(-p\\d*)?");
        Matcher matcher1 = pattern1.matcher(url);

        Pattern pattern2 = Pattern.compile("/movie/\\d+");
        Matcher matcher2 = pattern2.matcher(url);
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        
        page.addTargetRequests(page.getHtml().links().regex("http://www\\.80s\\.tw/movie/list/-[\\d*]+---[-p\\d*]?").all());
        
        //列表页面
        if(matcher1.find()){
            //电影详情页URL集合
            List<String> moviePageUrls = page.getHtml().xpath("//ul[@class='me1 clearfix']/li/a/@href").all();

            if(moviePageUrls != null && moviePageUrls.size() > 0){
                //将当前列表页的所有电影页面添加进去
                page.addTargetRequests(moviePageUrls);
            }

            //当前列表页中的其他列表页的链接
            List<String> listUrls = page.getHtml().xpath("//div[@class='pager']/a/@href").all();

            if(listUrls != null && listUrls.size() > 0){            
                page.addTargetRequests(listUrls);
            }

        }else if(matcher2.find()){  //电影页面   
        	String movieId = page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf("/")+1);
            //获取电影名字
            String movieName= page.getHtml().xpath("//div[@class='info']/h1/text()").toString()+  "----  "+page.getUrl().toString();
            //获取电影播放链接
            String movieUrl = page.getHtml().xpath("//li[@class='clearfix dlurlelement backcolor1']/span[@class='dlname nm']/input/@value").toString();

            Movie movie = new Movie(movieName, movieUrl);
            movie.setId(Integer.valueOf(movieId));
            
            page.putField("movie", movie);  //后面做数据的持久化
        }    
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MovieProcessor())
        .addUrl("http://www.80s.tw/movie/list/")
        .addPipeline(new FilePipeline("G:\\movie\\"))
        .addPipeline(new ConsolePipeline())
//        .addPipeline(new SaveDataPipeline())
        .thread(10)
        .run();
        System.out.println("****************************************************");
    }
}
