package newb.c.a_web.controller.component.webmagic;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
public class BearProcessor implements PageProcessor {

    public int i = 1 ;

    @Autowired(required=false)
    private MongoTemplate mongoTemplate;

    @Override
    public void process(Page page) {
        i+=1;
        String json =page.getJson().toString();
        JSONObject  object = (JSONObject) JSON.parse(json);
        System.out.println(object.toJSONString());

        page.addTargetRequest("http://98.126.159.33/V1/Product/detail?pid="+i+"&");

        mongoTemplate.insert(object);



    }


    @Override
    public Site getSite() {
        //添加 代理  让fiddler 监控 请求
        HttpHost httpProxy = new HttpHost("localhost",8888);
        //构建 添加 重试机制  header 和 cooker
        return Site.me()
                .setHttpProxy(httpProxy)
                .setTimeOut(30000)
                .setRetryTimes(3)
                .setSleepTime(1000)
                .setCharset("UTF-8")
                .addHeader("Accept-Encoding","gzip")
                .addHeader("Connection", "keep-alive")
                .setUserAgent("Dalvik/1.6.0 (Linux; U; Android 4.4.4; MI 5s Build/KTU84P)")
//                .addCookie("uid","")
                .addCookie("app_version","4.2.1")
                .addCookie("token","ca3168d725524713eafc4bec827dddc5")
                .addCookie("os","android,4.4.4")
                .addCookie("t",getNowTimeStamp())
                .addCookie("req_crypt","false")
                .addCookie("resp_crypt","false")
                ;
    }

    public static void main(String[] args) {
        //http://98.126.159.33/V1/Product/detail?pid=2229&
        Spider.create(new BearProcessor())
                .addUrl("http://98.126.159.33/V1/Product/detail?pid=1&")
//                .addPipeline(new FilePipeline("G:\\bear\\"))
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
        System.out.println("****************************************************");
    }

    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }
}
