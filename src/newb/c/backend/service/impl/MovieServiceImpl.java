package newb.c.backend.service.impl;


import newb.c.backend.model.basemodel.Movie;
import newb.c.backend.service.MovieService;

import org.springframework.stereotype.Service;

@Service("MovieService")
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {
	
}
