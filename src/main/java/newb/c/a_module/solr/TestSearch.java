package newb.c.a_module.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;

public class TestSearch {
	private static final String URL = "http://localhost:8080/solr/core1";
	private static HttpSolrClient solrClient = null;

	public static void main(String[] args) {
		TestSearch search = new TestSearch();
		search.init();
		search.addData();
	}

	private void init(){ 
		solrClient = new HttpSolrClient.Builder(URL).build(); 
		solrClient.setConnectionTimeout(3000); 
	}

	public void addData(){ 
		Map<String, SolrInputField> filedMap = new HashMap<String, SolrInputField>(); 
		SolrInputField fb = new SolrInputField("aa"); 
		fb.addValue("aa", 1);
		fb.addValue("bb", 2); 
		filedMap.put("aa", fb); 
		SolrInputDocument doc = new SolrInputDocument(filedMap); 
		doc.addField("id", 6666);
		try { 
			//solrClient.deleteById("id"); 
			solrClient.add(doc ); 
			solrClient.commit(); 
			} 
		catch (SolrServerException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (IOException e) { 
			System.out.println(e); 
			} 
		} 
	}
