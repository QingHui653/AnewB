package newb.c.a_spring.a_module.elasticsearch.v2;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.junit.Test;

public class CreateIndexAndMapping {
	
	

	/**
	 * 创建index索引
	 * //首先创建索引
	 */
//	@Test
	public void createIndex() {
		//构建一个Index（索引）首字母不能大写  
		CreateIndexRequest request = new CreateIndexRequest("wow");
		ESTools.client.admin().indices().create(request);
		
		System.out.println("创建 index 成功 ");
		
	}
	
	/**
	 * 创建mapping 索引中的mapping
	 * 在index 中创建mapping
	 */
	@Test
	public void createBangMapping(){
	    PutMappingRequest mapping = Requests.putMappingRequest("wow").type("Movie").source(MovieMapping.getMapping());
	    ESTools.client.admin().indices().putMapping(mapping).actionGet();
	}
	
}
