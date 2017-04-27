package newb.c.controller.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import newb.c.backend.model.basemodel.Movie;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class MongoDbSaveMoviePipeline implements Pipeline {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
    /**
     * 
     * 爬虫数据的持久化
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
    	Movie movie = resultItems.get("movie");
    	if (movie != null) {
    		mongoTemplate.insert(movie);
        }
    }
}
