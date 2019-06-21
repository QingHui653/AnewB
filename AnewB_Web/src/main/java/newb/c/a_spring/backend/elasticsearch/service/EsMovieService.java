package newb.c.a_spring.backend.elasticsearch.service;

import newb.c.a_spring.backend.elasticsearch.model.MovieDTO;
import newb.c.a_spring.backend.elasticsearch.repository.EsMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

//@Service
public class EsMovieService {

    @Autowired
    private EsMovieRepository esMovieRepository;

    public Iterable<MovieDTO> findAll(Pageable pageable){
        return esMovieRepository.findAll(pageable);
    }
}
