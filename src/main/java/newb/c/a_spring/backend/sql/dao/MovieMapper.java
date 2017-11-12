package newb.c.a_spring.backend.sql.dao;

import newb.c.a_spring.backend.sql.service.common.MyMapper;
import org.springframework.stereotype.Repository;

import newb.c.a_spring.backend.sql.model.basemodel.Movie;

@Repository
public interface MovieMapper extends MyMapper<Movie> {
	
}