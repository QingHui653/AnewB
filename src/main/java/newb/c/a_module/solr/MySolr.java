package newb.c.a_module.solr;

import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 

public class MySolr {
 
	//solr url
    public static final String URL = "http://localhost:8080/solr";
    //solr应用
    public static final String SERVER = "core1";
    //待索引、查询字段
    public static String[] docs = {"Solr是一个独立的企业级搜索应用服务器",
                                    "它对外提供类似于Web-service的API接口",
                                    "用户可以通过http请求",
                                     "向搜索引擎服务器提交一定格式的XML文件生成索引",
                                    "也可以通过Http Get操作提出查找请求",
                                    "并得到XML格式的返回结果"};
    

    public static void main(String[] args) {
        createIndex();
        search();
    }
    
    public static SolrClient getSolrClient(){
//      return new HttpSolrClient(URL+"/"+SERVER);
    	return new HttpSolrClient.Builder(URL+"/"+SERVER).build();
    }
 
    /**
     * 新建索引
     */
    public static void createIndex(){
        SolrClient client = getSolrClient();
        int i = 0;
        List<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
        for(String str : docs){
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id",i++);
            doc.addField("content_test", str);
            docList.add(doc);
        }
        try {
            client.add(docList);
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    /**
     * 全部删除
     */
    public static void remove(){
        SolrClient client = getSolrClient();
        SolrInputDocument doc = new SolrInputDocument();
        
        try {
			client.deleteByQuery("*");
			client.commit(); 
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}        

        System.out.println("Documents deleted");
    }
    
    /**
     * 搜索
     */
    public static void search(){
        SolrClient client = getSolrClient();
        SolrQuery query = new SolrQuery();
        
        query.setQuery("content_test:搜索");//设置查询条件
        //query.addField("*");//设置查询字段  默认全部
        
        QueryResponse response = null;
        try {
            response = client.query(query);
            System.out.println(response.toString());
            System.out.println();
            SolrDocumentList docs = response.getResults();
            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                System.out.println("id: " + doc.getFieldValue("id") + "      content: " + doc.getFieldValue("content_test"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 分组(faceting)指的是将搜索结果分类到各种类别中
     */
    public static void faceting() throws SolrServerException, IOException {
    	SolrClient client = getSolrClient();
    	//准备个文档
    	SolrInputDocument doc = new SolrInputDocument(); 

        SolrQuery query = new SolrQuery(); 

        query.setQuery("*:*"); 
        query.setRows(0); 

        //新建个分组字段
        query.addFacetField("author");        
        //请求
        QueryRequest qryReq = new QueryRequest(query); 
        //回应
        QueryResponse resp = qryReq.process(client);  
        
        System.out.println(resp.getFacetFields()); 

        List<FacetField> facetFields = resp.getFacetFields(); 
        for (int i = 0; i > facetFields.size(); i++) { 
           FacetField facetField = facetFields.get(i); 
           List<Count> facetInfo = facetField.getValues(); 

           for (FacetField.Count facetInstance : facetInfo) { 
              System.out.println(facetInstance.getName() + " : " + 
                 facetInstance.getCount() + " [drilldown qry:" + 
                 facetInstance.getAsFilterQuery()); 
           } 
           System.out.println("Hello"); 
        } 
     }
}
    

