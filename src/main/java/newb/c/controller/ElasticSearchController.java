package newb.c.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import newb.c.backend.elasticmodel.MovieDTO;
import newb.c.backend.elasticmodel.TaskInfoDTO;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Controller
@RequestMapping("resources/elasticsearch")
public class ElasticSearchController {

	@Autowired(required=false)
	private ElasticsearchTemplate elasticsearchTemplate;
	
	private String esIndexName = "heros";

	private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="queryMovie",method={RequestMethod.GET})
	@ApiOperation(value="查询电影")
	@ResponseBody
	public Object queryMovie(String name,Integer  page,Integer  pageSize) {
		if(StringUtils.isBlank(name))
			name=null;
		//条件查询 相等
		CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria().
				and(new Criteria("movieName").is(name)))
				.setPageable(new PageRequest(page-1, pageSize));
		List<MovieDTO> movieList = elasticsearchTemplate.queryForList(criteriaQuery, MovieDTO.class);	
		return movieList;
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value="querylikeMovie",method={RequestMethod.GET})
	@ApiOperation(value="查询电影")
	@ResponseBody
	public Object querylikeMovie(String name,Integer  page,Integer  pageSize) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("movieName", name)).withPageable(new PageRequest(page-1,pageSize)).build();

		List<MovieDTO> movieList = elasticsearchTemplate.queryForList(searchQuery, MovieDTO.class);
//		movieList.forEach(System.out::println);
		return movieList;
	}
	
	
	@GetMapping("CreatIndexMapping")
	@ApiOperation(value="创建elastic index和mapping")
	private void CreatIndexMapping(String esIndexName) {
		// 首先判断index 是否存在 创建 index
		if (!elasticsearchTemplate.indexExists("")){
			elasticsearchTemplate.createIndex(esIndexName);
		}
		// 在创建mapping ，需要在实体类中使用注解
		elasticsearchTemplate.putMapping(TaskInfoDTO.class);
	}
	
	/**
	 * 上github 查看源码 test core 下 query包
	 * @param query
	 */
	@SuppressWarnings("static-access")
	@GetMapping("queryById")
	@ApiOperation(value="查询样例")
	public void query(String query) {
		CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
				.and(new Criteria("clusterName").is("app"))
				.and(new Criteria("ip").is("127.0.0.1"))
				.and(new Criteria("appType").is("download"))
				.and(new Criteria("appName").is("appdownload"))
				.and(new Criteria("fileName").is("appdownload.log"))
				.and(new Criteria("logLeval").is("info"))
				.and(
						new Criteria("produceDateTime")
						.greaterThanEqual("startDate.getTime()")
						.lessThanEqual("endDate.getTime()")
					)
				.and(new Criteria("message").contains("haha"))).setPageable(
				new PageRequest(0, 10)).addSort(
				new Sort(new Sort.Order(Sort.Direction.DESC, "segEndlineNo")));
		List<TaskInfoDTO> taskInfoList = elasticsearchTemplate.queryForList(criteriaQuery, TaskInfoDTO.class);	
		taskInfoList.forEach(System.out::println);
	}
	
	
	@PostMapping("insertOrUpdate")
	@ResponseBody
	@ApiOperation(value="插入和更新数据  单条")
	public Object insertOrUpdateTaskInfoDTO(TaskInfoDTO TaskInfoDTO) {
		try {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(TaskInfoDTO.getTaskId()).withObject(TaskInfoDTO).build();
			elasticsearchTemplate.index(indexQuery);
			return "true";
		} catch (Exception e) {
			logger.error("insert or update task info error.", e);
			return "false";
		}
	}
	
	@PostMapping("insertOrUpdateList")
	@ApiOperation(value="插入和更新数据  多条")
	@ResponseBody
	public Object insertOrUpdateTaskInfoDTOList(List<TaskInfoDTO> TaskInfoDTOList) {
		List<IndexQuery> queries = new ArrayList<IndexQuery>();
		for (TaskInfoDTO TaskInfoDTO : TaskInfoDTOList) {
			IndexQuery indexQuery = new IndexQueryBuilder().withId(TaskInfoDTO.getTaskId()).withObject(TaskInfoDTO)
					.build();
			queries.add(indexQuery);
		}
		elasticsearchTemplate.bulkIndex(queries);
		return "true";
	}
	
	@GetMapping("deleteById")
	@ApiOperation(value="通过id删除数据 某个")
	@ResponseBody
	public <T> Object deleteById(String id, Class<T> clzz) {
		try {
			elasticsearchTemplate.delete(clzz.getClass(),id);
			return "true";
		} catch (Exception e) {
			logger.error("delete " + clzz + " by id " + id + " error.", e);
			return "false";
		}
	}

	@GetMapping("deleteType")
	@ApiOperation(value="通过id删除数据")
	@ResponseBody
	public <T> Object deleteType(String index, String type, @RequestParam(defaultValue = "100000") Integer pageSize, Class<T> clzz) {
		try {
			DeleteQuery deleteQuery = new DeleteQuery();
			deleteQuery.setIndex(index);
			deleteQuery.setType(type);
			deleteQuery.setPageSize(pageSize);
			elasticsearchTemplate.delete(deleteQuery,MovieDTO.class);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	
	/**
	 * 检查健康状态
	 */
	public boolean ping() {
		try {
			ActionFuture<ClusterHealthResponse> health = elasticsearchTemplate.getClient().admin().cluster()
					.health(new ClusterHealthRequest());
			ClusterHealthStatus status = health.actionGet().getStatus();
			if (status.value() == ClusterHealthStatus.RED.value()) {
				throw new RuntimeException("elasticsearch cluster health status is red.");
			}
			return true;
		} catch (Exception e) {
			logger.error("ping elasticsearch error.", e);
			return false;
		}
	}
	
	/********查询 看代码未使用elasticsearchTemplate 还是使用elasticsearch自带的查询，版本有点低 ，建议对照 elasticsearch包下 query 看***********/
	/** 查询 id */    
    public List<String> queryId(String type, String[] fields, String content,String sortField, SortOrder order, int from, int size) {    
        SearchRequestBuilder reqBuilder = elasticsearchTemplate.getClient().prepareSearch(esIndexName)    
                														   .setTypes(type).setSearchType(SearchType.DEFAULT)    
                														   .setExplain(true);    
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""+ content + "\"");    
        for (String k : fields) {    
            queryString.field(k);    
        }    
        queryString.minimumShouldMatch("10");    
        reqBuilder.setQuery(QueryBuilders.boolQuery().should(queryString)).setExplain(true);    
        if (StringUtils.isNotEmpty(sortField) && order != null) {    
            reqBuilder.addSort(sortField, order);    
        }    
        if (from >= 0 && size > 0) {    
            reqBuilder.setFrom(from).setSize(size);    
        }    
        SearchResponse resp = reqBuilder.execute().actionGet();    
        SearchHit[] hits = resp.getHits().getHits();    
        ArrayList<String> results = new ArrayList<String>();    
        for (SearchHit hit : hits) {    
            results.add(hit.getId());    
        }    
        return results;    
    }    
    
    /**   
     * 查询得到结果为Map集合   
     */    
    public List<Map<String, Object>> queryForObject(String type,String[] fields, String content, String sortField, 
    		SortOrder order,int from, int size) {    
        SearchRequestBuilder reqBuilder = elasticsearchTemplate.getClient().prepareSearch(esIndexName)    
                													       .setTypes(type).setSearchType(SearchType.DEFAULT)    
                													       .setExplain(true);    
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""+ content + "\"");    
        for (String k : fields) {    
            queryString.field(k);    
        }    
        queryString.minimumShouldMatch("10");    
        reqBuilder.setQuery(QueryBuilders.boolQuery().should(queryString)).setExplain(true);    
        if (StringUtils.isNotEmpty(sortField) && order != null) {    
            reqBuilder.addSort(sortField, order);    
        }    
        if (from >= 0 && size > 0) {    
            reqBuilder.setFrom(from).setSize(size);    
        }    
    
        SearchResponse resp = reqBuilder.execute().actionGet();    
        SearchHit[] hits = resp.getHits().getHits();    
    
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();    
        for (SearchHit hit : hits) {    
            results.add(hit.getSource());    
        }    
        return results;    
    }    
    
    /**   
     * QueryBuilders 所有查询入口   
     */    
    public List<Map<String, Object>> queryForObjectEq(String type,    
            String[] fields, String content, String sortField, SortOrder order,    
            int from, int size) {    
        SearchRequestBuilder reqBuilder = elasticsearchTemplate.getClient().prepareSearch(esIndexName)    
                														   .setTypes(type).setSearchType(SearchType.DEFAULT)    
                														   .setExplain(true);    
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""+ content + "\"");    
        for (String k : fields) {    
            queryString.field(k);    
        }    
        queryString.minimumShouldMatch("10");    
        reqBuilder.setQuery(QueryBuilders.boolQuery().must(queryString)).setExplain(true);    
        if (StringUtils.isNotEmpty(sortField) && order != null) {    
            reqBuilder.addSort(sortField, order);    
        }    
        if (from >= 0 && size > 0) {    
            reqBuilder.setFrom(from).setSize(size);    
        }    
    
        SearchResponse resp = reqBuilder.execute().actionGet();    
        SearchHit[] hits = resp.getHits().getHits();    
    
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();    
        for (SearchHit hit : hits) {    
            results.add(hit.getSource());    
        }    
        return results;    
    }    
    
    /**   
     * 多个文字记不清是那些字,然后放进去查询   
     */    
    public List<Map<String, Object>> queryForObjectNotEq(String type,    
            String field, Collection<String> countents, String sortField,    
            SortOrder order, int from, int size) {    
    
        SearchRequestBuilder reqBuilder = elasticsearchTemplate.getClient().prepareSearch(esIndexName)    
                														   .setTypes(type).setSearchType(SearchType.DEFAULT)    
                														   .setExplain(true);    
        List<String> contents = new ArrayList<String>();    
        for (String content : countents) {    
            contents.add("\"" + content + "\"");    
        }    
        TermsQueryBuilder inQuery = QueryBuilders.termsQuery(field, contents);    
        inQuery.minimumShouldMatch("10");    
        reqBuilder.setQuery(QueryBuilders.boolQuery().mustNot(inQuery)).setExplain(true);    
        if (StringUtils.isNotEmpty(sortField) && order != null) {    
            reqBuilder.addSort(sortField, order);    
        }    
        if (from >= 0 && size > 0) {    
            reqBuilder.setFrom(from).setSize(size);    
        }    
    
        SearchResponse resp = reqBuilder.execute().actionGet();    
        SearchHit[] hits = resp.getHits().getHits();    
    
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();    
        for (SearchHit hit : hits) {    
            results.add(hit.getSource());    
        }    
        return results;    
    }    
    
    /**   
     * Filters 查询方式   (已经被废弃)  全部使用 QueryBuilders
     *    
     * 1. 1)QueryBuilders.queryString 获得基本查询   
     *    2)FilteredQueryBuilder query = QueryBuilders.filteredQuery(queryString,FilterBuilder)   
     *    3)通过上面封装成为查询,将这个query插入到reqBuilder中;完成操作   
     *       
     * 2.在   reqBuilder.setQuery(query);   
     *    
     * 3.介绍在2)中的FilterBuilder各种构造方式-参数都可以传String类型即可   
     * FilterBuilders.rangeFilter("taskState").lt(20) 小于 、 lte(20) 小于等于   
     * FilterBuilders.rangeFilter("taskState").gt(20)) 大于  、 gte(20) 大于等于   
     * FilterBuilders.rangeFilter("taskState").from(start).to(end)) 范围,也可以指定日期,用字符串就ok了   
     */    
    public List<Map<String, Object>> queryForObjectForElasticSerch(String type,    
            String field, String content,int start,int end) {    
    
        SearchRequestBuilder reqBuilder = elasticsearchTemplate.getClient().prepareSearch(esIndexName)    
                .setTypes(type).setSearchType(SearchType.DEFAULT)    
                .setExplain(true);    
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""    
                + content + "\"");    
            queryString.field(field);    
        queryString.minimumShouldMatch("10");    
            
        reqBuilder.setQuery(QueryBuilders.filteredQuery(queryString, QueryBuilders.rangeQuery("taskState").from(start).to(end)))    
                .setExplain(true);    
    
        SearchResponse resp = reqBuilder.execute().actionGet();    
        SearchHit[] hits = resp.getHits().getHits();    
    
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();    
        for (SearchHit hit : hits) {    
            results.add(hit.getSource());    
        }    
        return results;    
    }    
    
    public void afterPropertiesSet() throws Exception {    
        System.out.println("init...");    
    
    }
}
