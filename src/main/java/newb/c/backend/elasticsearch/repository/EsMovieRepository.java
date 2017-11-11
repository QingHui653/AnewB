package newb.c.backend.elasticsearch.repository;

import newb.c.backend.elasticsearch.model.MovieDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 报错 生成失败 ???
 * mapper [id] of different type, current_type [long], merged_type [string]
 */
//@Repository
public interface EsMovieRepository extends PagingAndSortingRepository<MovieDTO, String> {

}
