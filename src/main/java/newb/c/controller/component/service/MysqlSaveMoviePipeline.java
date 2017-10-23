package newb.c.controller.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import newb.c.backend.model.basemodel.Movie;
import newb.c.backend.service.MovieService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

//@Service
public class MysqlSaveMoviePipeline implements Pipeline {
	
	@Autowired() 
	private MovieService movieService;
	
    /**
     * 爬虫数据的持久化
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
    	Movie movie = resultItems.get("movie");
    	/*if (movie != null) {
    		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
    		valueOper.set(movie.getMovieName(), movie.getMovieLink());
        }*/
    }
}
