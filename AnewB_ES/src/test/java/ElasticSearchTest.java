import newb.c.a_spring.backend.elasticsearch.model.MovieDTO;
import newb.c.a_spring.backend.elasticsearch.model.ProductEs;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:/config/elasticsearch/elasticsearch5.xml"})
@ContextConfiguration({"classpath:/config/elasticsearch/spring-elasticsearch.xml"})
public class ElasticSearchTest {
	
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

	@Test
	public void queryProduct() {
		String name = "k";
		Integer page = 1;
		Integer pageSize = 500;
		Criteria query = new Criteria()
				.or(new Criteria("productName").contains(name))
				.or(new Criteria("remark").contains(name))
				.or(new Criteria("barcodes").contains("2003070003"));
		query.getCriteriaChain().forEach(System.out::println);
		CriteriaQuery criteriaQuery = new CriteriaQuery(query)
				.setPageable(PageRequest.of(page - 1, pageSize));
		List<ProductEs> resList = elasticsearchTemplate.queryForList(criteriaQuery, ProductEs.class);
		System.out.println("size " + resList.size());
		resList.forEach(System.out::println);

		Page<ProductEs> pageList = elasticsearchTemplate.queryForPage(criteriaQuery, ProductEs.class);
		System.out.println("pages="+pageList.getTotalPages()+";nums="+pageList.getTotalElements());
		pageList.getContent().forEach(System.out::println);
	}

	@Test
	public void queryProductPage(){
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		//查询采购单
		boolQueryBuilder.must((QueryBuilders.termQuery("corpId", "")));
		//模糊查询: 单号,上游（姓名/手机/店名）、商品（品名、条码）、
		//ngram 为分词选择
		boolQueryBuilder.must((QueryBuilders.multiMatchQuery("req.getSearchText()","productName.ngram","remark.ngram","barcodes.ngram")));
		//时间范围
		boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime").from("req.getStartTime()").to("req.getEndTime()"));

		Sort sort = new Sort(Sort.Direction.DESC, "createTime") ;
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(boolQueryBuilder)
				.withPageable(PageRequest.of(1, 20,sort))
				.build();
		//查询 Page
		Page<ProductEs> productEs = elasticsearchTemplate.queryForPage(nativeSearchQuery, ProductEs.class);

		//查询amount
		SumAggregationBuilder sumBuilder = AggregationBuilders.sum("amount").field("amount");
		NativeSearchQuery sumQuery = new NativeSearchQueryBuilder()
				.withQuery(boolQueryBuilder)
				.addAggregation(sumBuilder)
				.build();
		double sumSales = elasticsearchTemplate.query(sumQuery,response -> {
			InternalSum sum = (InternalSum)response.getAggregations().asList().get(0);
			return sum.getValue();
		});
		//查询count
		NativeSearchQuery countQuery = new NativeSearchQueryBuilder()
				.withQuery(boolQueryBuilder)
				.build();
		long count = elasticsearchTemplate.count(countQuery);
	}
}
