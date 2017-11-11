package newb.c.backend.sql.dao;

import newb.c.backend.sql.service.common.MyMapper;
import org.springframework.stereotype.Repository;

import newb.c.backend.sql.model.basemodel.Movie;

@Repository
public interface MovieMapper extends MyMapper<Movie> {
	
}