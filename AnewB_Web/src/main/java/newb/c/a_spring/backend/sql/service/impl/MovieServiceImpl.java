package newb.c.a_spring.backend.sql.service.impl;


import lombok.extern.slf4j.Slf4j;
import newb.c.a_spring.backend.sql.dao.MovieMapper;
import newb.c.a_spring.backend.sql.dao.NoveMapper;
import newb.c.a_spring.backend.sql.model.basemodel.Movie;
import newb.c.a_spring.backend.sql.model.basemodel.Nove;
import newb.c.a_spring.backend.sql.service.MovieService;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("MovieService")
@Slf4j
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private NoveMapper noveMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testAOrB() {
        for (int i = 0; i <50 ; i++) {
            Movie movie= new Movie();
            movie.setId(i);
            movie.setMovieLink("i");
            movie.setMovieName("i");
            movieMapper.insert(movie);
//            insertB(String.valueOf(i));
            // jdk 代理 会 导致 B 事务不生效 参考 https://blog.csdn.net/xlgen157387/article/details/79026285
            ((MovieService) AopContext.currentProxy()).insertB(String.valueOf(i));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertB(String i){
        Nove nove= new Nove();
        nove.setId(String.valueOf(i));
        noveMapper.insert(nove);

        if("40".equals(i))
            throw new RuntimeException("B报错");
    }
}
