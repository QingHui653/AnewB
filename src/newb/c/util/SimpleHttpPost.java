package newb.c.util;

import java.io.IOException;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class SimpleHttpPost  {
	
	public static String response(String url,String value){
		String response=null;
		HttpClient httpclient=HttpClients.createDefault();
		HttpPost  httpPost=new HttpPost(url);
		StringEntity entity = new StringEntity(value, Consts.UTF_8);
		httpPost.setEntity(entity);
		try {
			HttpResponse httpResponse=httpclient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				HttpEntity httpEntity=httpResponse.getEntity();
				
				response=EntityUtils.toString(httpEntity, "utf-8");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
