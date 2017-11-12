package newb.c.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import newb.c.a_spring.backend.elasticsearch.model.MovieDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:main/resources/config.xml","classpath:main/resources/elasticsearch/elasticsearch.xml"})
public class ElasticSearchUtil {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void queryMovie() {
		String name="爱";
		Integer page=1;
		Integer pageSize=500;
		CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
				.and(new Criteria("movieName").contains(name)))
//				.contains(name))
				.setPageable(new PageRequest(page-1, pageSize));
		List<MovieDTO> movieList = elasticsearchTemplate.queryForList(criteriaQuery, MovieDTO.class);	
		System.out.println("size "+movieList.size());
		movieList.forEach(System.out::println);
	}
}
