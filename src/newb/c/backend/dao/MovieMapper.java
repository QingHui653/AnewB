package newb.c.backend.dao;

import org.springframework.stereotype.Repository;

import newb.c.backend.model.basemodel.Movie;
import newb.c.backend.service.common.MyMapper;

@Repository
public interface MovieMapper extends MyMapper<Movie> {
	
}