package newb.c.api;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("https://github.com/QingHui653/\\w+")
@HelpUrl("https://github.com/QingHui653")
public class github {
	
	@ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
	private String author;
	
	@ExtractBy("//h1[@class='entry-title public']/strong/a/text()")
	private String name;
	
	@ExtractBy("//div[@id='readme']/tidyText()")
	private String readme;
	
	public static void main(String[] args) {
		OOSpider.create(Site.me().setSleepTime(1000)
                ,new ConsolePageModelPipeline(), github.class)
                .addUrl("https://github.com/QingHui653").thread(5).run();
        System.out.println("****************************************************");
	}
}
