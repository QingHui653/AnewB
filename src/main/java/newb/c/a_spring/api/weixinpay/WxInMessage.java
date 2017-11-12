package newb.c.a_spring.api.weixinpay;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class WxInMessage {

public  HashMap<String, String> wxHashMap=new HashMap<String, String>();

public String getValueByKey(String key){
	if(wxHashMap.get(key) != null){
		return wxHashMap.get(key).toString();
	}else{
		return "";
	}
}
  public static WxInMessage fromXml(String xml) {
	  WxInMessage wxInMessage=new WxInMessage();
	  try {
		Document document = DocumentHelper.parseText(xml);
		Element root=document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element>  list=root.elements();
		for(Element node :list){

			wxInMessage.wxHashMap.put(node.getName(), node.getText());
			
		}
	} catch (DocumentException e) {
		
		
	}
	  return wxInMessage;

  }

//public static String xmlhandler (){
//	JsonObject jo1=new JsonObject();
//	jo1.addProperty("touser", "ob6Xqvo973lgj53_tZHMRKsukAIo");
//	jo1.addProperty("msgtype", "news");
//	JsonArray  jA=new JsonArray();
//	JsonObject  jo3=new JsonObject();
//	jo3.addProperty("title", "Happy Day");
//	jo3.addProperty("description", "is Really A Happy Day");
//	jo3.addProperty("url", "URL");
//	jo3.addProperty("picurl", "PICURL");
//	JsonObject jo4=new JsonObject();
//	jo4.addProperty("title", "happy  day");
//	jo4.addProperty("description", "is a haoo");
//	jo4.addProperty("url", "URL");
//	jo4.addProperty("picurl", "picurl");
//	jA.add(jo3);
//	jA.add(jo4);
//	JsonObject jo2=new JsonObject();
//	jo2.add("articles", jA);
//	jo1.add("news", jo2);
//	return jo1.toString();
//}
  public static WxInMessage fromXml(InputStream is) {
	  WxInMessage wxInMessage=new WxInMessage();
	  SAXReader sr = new SAXReader();//获取读取xml的对象。
	  try {
		Document document=sr.read(is);
		Element root=document.getRootElement();
		
		List<Element>  list=root.elements();
		
		for(Element node :list){
			wxInMessage.wxHashMap.put(node.getName(), node.getText());
			
		}
	} catch (DocumentException e) {
		
		e.printStackTrace();
		throw(new NullPointerException());
	}
	  return wxInMessage;
    
  }

  



}

  

   