package newb.c.elasticsearch;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class MovieMapping {

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
									.startObject("movie_name")
										 .field("type","string")
										 .field("index","not_analyzed")
									.endObject()
									.startObject("movie_link")
										 .field("type","string")
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
