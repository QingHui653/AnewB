package newb.c.backend.elasticsearch.service;

import newb.c.backend.elasticsearch.model.MovieDTO;
import newb.c.backend.elasticsearch.repository.EsMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//@Service
public class EsMovieService {

    @Autowired
    private EsMovieRepository esMovieRepository;

    public Iterable<MovieDTO> findAll(Pageable pageable){
        return esMovieRepository.findAll(pageable);
    }
}
