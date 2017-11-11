package newb.c.backend.sql.service.impl;


import newb.c.backend.sql.model.basemodel.Movie;
import newb.c.backend.sql.service.MovieService;

import org.springframework.stereotype.Service;

@Service("MovieService")
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {
	
}
