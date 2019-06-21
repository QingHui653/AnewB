package newb.c.a_web.controller.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import newb.c.a_spring.backend.sql.model.basemodel.Movie;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service
public class MongoDbSaveMoviePipeline implements Pipeline {
	
	
	@Autowired(required=false)
	private MongoTemplate mongoTemplate;
    /**
     * 
     * 爬虫数据的持久化
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
    	Movie movie = resultItems.get("movie");
    	
//    	Query query = new Query();
//        Criteria criteria =new Criteria();
    	
    	if (movie != null) {
    		//这样好像没用，会经常返回其他数据
//    		criteria.where("movieLink").is(movie.getMovieLink());
//    		query.addCriteria(criteria);
//    		List<Movie> ext= mongoTemplate.find(query, Movie.class);
//    		if(ext.size()==0)
    		mongoTemplate.insert(movie);
        }
    }
}
