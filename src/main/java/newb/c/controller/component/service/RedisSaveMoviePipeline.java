package newb.c.controller.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import newb.c.backend.sql.model.basemodel.Movie;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

//@Service
public class RedisSaveMoviePipeline implements Pipeline {
	
	@Autowired(required=false)
	private RedisTemplate<String, String> redisTemplate;
    /**
     * 爬虫数据的持久化
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
    	Movie movie = resultItems.get("movie");
    	if (movie != null) {
    		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
    		valueOper.set(movie.getMovieName(), movie.getMovieLink());
        }
    }
}
