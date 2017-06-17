package newb.c.a_module.elasticsearch;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class ZhiDaoMapping {

	public static XContentBuilder getMapping(){
			
			XContentBuilder mapping =null;
			
			try {
				mapping = XContentFactory
					.jsonBuilder()
					.startObject()
						//开启倒计时功能
						.startObject("_ttl")
								.field("enabled",false)
								.endObject()
									.startObject("properties")
									.startObject("title")
										 .field("type","string")
									.endObject()
									.startObject("question")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("answer")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("category")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("author")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("date")
										 .field("type","string")
									     .field("index","not_analyzed")
									.endObject()
									.startObject("answer_author")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("answer_date")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("description")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("keywords")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("read_count")
										 .field("type","integer")
										 .field("index","not_analyzed")
									.endObject()
									//关联数据
									.startObject("list")
										 .field("type","object")
								    .endObject()
							  .endObject()
						.endObject();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return mapping;
		}
}
