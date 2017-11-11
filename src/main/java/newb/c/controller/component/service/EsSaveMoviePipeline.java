package newb.c.controller.component.service;

import newb.c.backend.elasticsearch.model.MovieDTO;
import newb.c.backend.sql.model.basemodel.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service
public class EsSaveMoviePipeline implements Pipeline {


    @Autowired(required=false)
    private ElasticsearchTemplate elasticsearchTemplate;
    /**
     * 
     * 爬虫数据的持久化
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
    	Movie movie = resultItems.get("movie");
        MovieDTO esMovie = new MovieDTO();
    	if (movie != null) {
            esMovie.setId(movie.getId());
            esMovie.setMovieName(movie.getMovieName());
            esMovie.setMovieLink(movie.getMovieLink());
            IndexQuery indexQuery = new IndexQueryBuilder().withId(esMovie.getId().toString()).withObject(esMovie).build();
            elasticsearchTemplate.index(indexQuery);
        }
    }
}
