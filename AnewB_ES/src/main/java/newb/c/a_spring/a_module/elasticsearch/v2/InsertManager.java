package newb.c.a_spring.a_module.elasticsearch.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;

public class InsertManager {
	
	/**
	 * 添加数据到Elasticsearch
	 * @param index		索引
	 * @param type		类型
	 * @param idName	Id字段名称
	 * @param json		存储的JSON，可以接受Map
	 * @return
	 */
	public Map<Object,Object> save(String index, String type, String idName, JSONObject json) {
		List<Object> list =new ArrayList<>();
		list.add(json);
		return save(index,type,idName,list);
	}
	
	/**
	 * 添加数据到Elasticsearch
	 * @param index		索引
	 * @param type		类型
	 * @param idName	Id字段名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Object, Object> save(String index, String type, String idName, List<Object> list) {
		BulkRequestBuilder bulkRequest =ESTools.client.prepareBulk();
		Map<Object, Object> resultMap =new HashMap<>();
		
		for (Object object : list) {
			JSONObject json = (JSONObject)object;
			System.out.println("--传入json -- "+json);
			//没有指定idName 那就让Elasticsearch自动生成
			if(StringUtils.isBlank(idName)){
				IndexRequestBuilder Irb =ESTools.client
											.prepareIndex(index, type)
												.setSource(json);
				bulkRequest.add(Irb);
				
			}else {
				String idValue= json.getString(idName);
				IndexRequestBuilder Irb =ESTools.client
												.prepareIndex(index, type,idValue)
												.setSource(json);
				bulkRequest.add(Irb);
						
			}
		}
		
		BulkResponse bulkResponse =bulkRequest.execute().actionGet();
		
		if(bulkResponse.hasFailures()){
			System.out.println(bulkResponse.getItems().toString());	
			resultMap.put("500", "保存ES失败!");
			return resultMap;
		}
		
		bulkRequest = ESTools.client.prepareBulk();
		resultMap.put("200", "保存ES成功!");
		return resultMap;
	}
	
	
}
