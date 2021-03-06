package newb.c.a_spring.a_module.elasticsearch.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import newb.c.a_spring.backend.elasticsearch.model.MovieDTO;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import newb.c.util.common.ResultUtil;

public class QueryManage {
	
	/**
	 *	根据id查询 
	 */
	public MovieDTO findById(String index,String type,String id) {
		MovieDTO movie =new MovieDTO();
		
		GetRequest rq= new GetRequest(index,type,id);
		
		GetResponse response = ESTools.client.get(rq).actionGet();
		
		if(!response.isSourceEmpty()){
			Map<String,Object> data =response.getSource();
			String json = JSON.toJSONString(data);
			movie=JSON.parseObject(json,MovieDTO.class);
		}
		return movie;
	}
	
	/**
	 * 根据查询list
	 * @param id
	 * @return
	 */
	public List<Object> findListById(String index,String type,String id) {
		Client client =ESTools.client;
		
		SearchResponse response =client.prepareSearch(index)
									   .setTypes(type)
									   .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
									   .setQuery(QueryBuilders.termQuery("id", id))// Query
								       .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
									   .setFrom(0).setSize(60).setExplain(true)
									   .execute()
									   .actionGet();
		SearchHits hits =response.getHits();
		
		List<Object> list =new ArrayList<>();
		
		for (SearchHit searchHit : hits) {
			Map<String,Object> source =searchHit.getSource();

			MovieDTO movie = JSONObject.parseObject(JSONObject.toJSONString(source),MovieDTO.class);
			
			list.add(movie);
		}
		
		return list;
	}
	
	/**
	 *	根据id查询List 
	 */
	public MovieDTO findByKey(String index,String type,String key){
		String prefix = "%sx_x%s";
		String id = String.format(prefix, "id","Gid");
		GetRequest rq = new GetRequest(index, type, id);
		//GetResponse response = client.prepareGet(MappingManager.INDEX, MappingManager.TYPE, id);
		GetResponse response = ESTools.client.get(rq).actionGet();
		MovieDTO entity = null;
		//判断非空
		if(!response.isSourceEmpty()){
			Map<String,Object> data = response.getSource();
			entity = JSONObject.parseObject(JSONObject.toJSONString(data),MovieDTO.class);
		}
		return entity;
	}
	
	/**
	 * 分页查询 
	 * @param resultMap
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public static ResultUtil findByPage(Map<String,Object> resultMap, 
			Integer pageSize,
			Integer pageNo,
			String index,
			String type){
		ResultUtil page = new ResultUtil();
		pageNo = null==pageNo?1:pageNo;
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		
		Client client = ESTools.client;
		SearchRequestBuilder srb = client.prepareSearch("hi");
		srb.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		srb.setTypes("hello");
//		srb.setQuery(resultMap);
		srb.setFrom((pageNo - 1) * pageSize).setSize(pageSize)
		.setExplain(true);
		
		SearchResponse response = srb.execute().actionGet();
		SearchHits hits = response.getHits();
		
		page.setTotalCount((int)hits.getTotalHits());
		List<Object> list = new ArrayList<>();
		for (SearchHit searchHit : hits) {
			Map<String,Object> source = searchHit.getSource();
			MovieDTO entity = JSONObject.parseObject(JSONObject.toJSONString(source),MovieDTO.class);
			list.add(entity);
		}
		page.setResultBeanList(list);
		return page;
		
	}
	/**
	 * 分页查询
	 * @param resultMap
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public static ResultUtil findSOBanggByPage(Map<String,Object> resultMap,
			Integer pageSize,
			Integer pageNo,
			String index,
			String type){
		ResultUtil page = new ResultUtil();
		pageNo = null==pageNo?1:pageNo;
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		
		Client client = ESTools.client;
		SearchRequestBuilder srb = client.prepareSearch("hi");
		srb.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		srb.setTypes("hello");
		
		srb.setQuery(QueryBuilders.termQuery("status",0));
		srb.setFrom((pageNo - 1) * pageSize).setSize(pageSize)
		.setExplain(true);
		
		SearchResponse response = srb.execute().actionGet();
		SearchHits hits = response.getHits();
		
		page.setTotalCount((int)hits.getTotalHits());
		List<Object> list = new ArrayList<>();
		for (SearchHit searchHit : hits) {
			Map<String,Object> source = searchHit.getSource();
			MovieDTO entity = JSONObject.parseObject(JSONObject.toJSONString(source),MovieDTO.class);
			list.add(entity);
		}
		page.setResultBeanList(list);
		return page;
		
	}
	
	
}
