package newb.c.controller;


import java.util.ArrayList;    
import java.util.List;    
    
import org.elasticsearch.action.ActionFuture;    
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;    
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;    
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;    
import org.springframework.data.elasticsearch.core.query.IndexQuery;    
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import newb.c.backend.model.TaskInfoDTO;

public class ElasticSearchController {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);
	private void init() {
		//首先判断index 是否存在  创建 index
		if(!elasticsearchTemplate.indexExists(""))
			elasticsearchTemplate.createIndex("");
		//在创建mapping ，需要在User中使用注解
		elasticsearchTemplate.putMapping(TaskInfoDTO.class);
	}
	
	 public boolean update(List<TaskInfoDTO> TaskInfoDTOList) {    
	        List<IndexQuery> queries = new ArrayList<IndexQuery>();    
	        for (TaskInfoDTO TaskInfoDTO : TaskInfoDTOList) {    
	            IndexQuery indexQuery = new IndexQueryBuilder().withId(TaskInfoDTO.getTaskId()).withObject(TaskInfoDTO).build();    
	            queries.add(indexQuery);    
	        }    
	        elasticsearchTemplate.bulkIndex(queries);    
	        return true;    
	    }    
	    
	    public boolean insertOrUpdateTaskInfoDTO(List<TaskInfoDTO> TaskInfoDTOList) {    
	        List<IndexQuery> queries = new ArrayList<IndexQuery>();    
	        for (TaskInfoDTO TaskInfoDTO : TaskInfoDTOList) {    
	            IndexQuery indexQuery = new IndexQueryBuilder().withId(TaskInfoDTO.getTaskId()).withObject(TaskInfoDTO).build();    
	            queries.add(indexQuery);    
	        }    
	        elasticsearchTemplate.bulkIndex(queries);    
	        return true;    
	    }    
	    
	    
	    public boolean insertOrUpdateTaskInfoDTO(TaskInfoDTO TaskInfoDTO) {    
	        try {    
	            IndexQuery indexQuery = new IndexQueryBuilder().withId(TaskInfoDTO.getTaskId()).withObject(TaskInfoDTO).build();    
	            elasticsearchTemplate.index(indexQuery);    
	            return true;    
	        } catch (Exception e) {    
	            logger.error("insert or update task info error.", e);    
	            return false;    
	        }    
	    }    
	    
	    
	    public <T> boolean deleteById(String id, Class<T> clzz) {    
	        try {    
	            elasticsearchTemplate.delete(clzz, id);    
	            return true;    
	        } catch (Exception e) {    
	            logger.error("delete " + clzz + " by id " + id + " error.", e);    
	            return false;    
	        }    
	    }    
	    
	    /**   
	     * 检查健康状态   
	    * @author 高国藩   
	    * @date 2015年6月15日 下午6:59:47   
	    * @return   
	     */    
	    public boolean ping() {    
	        try {    
	            ActionFuture<ClusterHealthResponse> health = elasticsearchTemplate.getClient().admin().cluster().health(new ClusterHealthRequest());    
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
	    
}
