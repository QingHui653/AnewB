package newb.c.a_spring.backend.elasticsearch.repository;

import newb.c.a_spring.backend.elasticsearch.model.MovieDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 报错 生成失败 ???
 * mapper [id] of different type, current_type [long], merged_type [string]
 */
@Repository
//public interface EsMovieRepository extends PagingAndSortingRepository<MovieDTO, String> {
public interface EsMovieRepository extends ElasticsearchRepository<MovieDTO, String> {

}
