package newb.c.a_spring.api.weixinpay;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;


import newb.c.util.SimpleHttpPost;



public class CopyOfWeiXinPayUtil {
	private static final Logger log=Logger.getLogger(CopyOfWeiXinPayUtil.class.getName());
    
	private  String timestamp=String.valueOf( System.currentTimeMillis() / 1000);
	public String genProductArgs(String id,String type,String biaoji,String totalFee) {
		StringBuffer xml = new StringBuffer();

		try {
			String	nonceStr = genNonceStr();
			
			
			xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            //按照ABCD来传
			packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
			packageParams.add(new BasicNameValuePair("attach", id+","+type+","+biaoji));//中文无法识别
			
//			packageParams.add(new BasicNameValuePair("attach", "testesteste"));//中文无法识别
			packageParams.add(new BasicNameValuePair("body", new String("支付运费".getBytes(), "utf-8")));//中文无法识别，//按照ABCD来传
			
//			packageParams.add(new BasicNameValuePair("body", "testyunfei"));//中文无法识别，//按照ABCD来传
			
			packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
			packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
			packageParams.add(new BasicNameValuePair("notify_url", "http://115.28.58.245:80/chengszj/paySuccessWX.action"));
//			packageParams.add(new BasicNameValuePair("out_trade_no",genOutTradNo()));
			packageParams.add(new BasicNameValuePair("out_trade_no",genSuiJiTime()));
			packageParams.add(new BasicNameValuePair("spbill_create_ip","127.0.0.1"));
			packageParams.add(new BasicNameValuePair("total_fee", "1000"));
//			packageParams.add(new BasicNameValuePair("total_fee", totalFee));
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));
			
			String sign = genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));


		   String xmlstring =toXml(packageParams);

//			return xmlstring;
//			return new String(xmlstring.toString().getBytes(), "ISO8859-1");//这句加上就可以了吧xml转码下
			return new String(xmlstring.toString().getBytes(), "utf-8");//这句加上就可以了吧xml转码下
			

		} catch (Exception e) {
			log.debug( "genProductArgs fail, ex = " + e.getMessage());
			return null;
		}
		

	}
	
	
	
	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}
	
	private String genSuiJiTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s+toRandom(4);
		
	}
	public static String toRandom(int x){
		 Random random = new Random();
		 String result="";
		 for(int i=0;i<x;i++){
		 result+=random.nextInt(10);
		 }
		 return result;
		 }
	
	/**
	 生成签名
	 */

	private String genPackageSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constants.API_KEY);
		
        
		
//		String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		String packageSign = MD5Util.MD5Encode(sb.toString(), "").toUpperCase();
		
		log.debug("orion1="+packageSign);
		return packageSign;
	}
	
	private String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<"+params.get(i).getName()+">");


			sb.append(params.get(i).getValue());
			sb.append("</"+params.get(i).getName()+">");
		}
		sb.append("</xml>");

		log.debug("orion2---->"+sb.toString());
		return sb.toString();
//		byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));//正确的写法
	}
	
	
	public String getSign(String prepay_id){
		String rst="";
		
		
		
		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
		signParams.add(new BasicNameValuePair("noncestr", genNonceStr()));
		signParams.add(new BasicNameValuePair("package", "Sign=WXPay"));
		signParams.add(new BasicNameValuePair("partnerid", Constants.MCH_ID));
		signParams.add(new BasicNameValuePair("prepayid", prepay_id));
		signParams.add(new BasicNameValuePair("timestamp", timestamp));

		rst= genAppSign(signParams);
		
		
		
		return rst;
	}
	
	
//	private String genAppSign(List<NameValuePair> params) {
//		StringBuilder sb = new StringBuilder();
//
//		for (int i = 0; i < params.size(); i++) {
//			sb.append(params.get(i).getName());
//			sb.append('=');
//			sb.append(params.get(i).getValue());
//			sb.append('&');
//		}
//		sb.append("key=");
//		sb.append(Constants.API_KEY);
//
////        sb.append("sign str\n"+sb.toString()+"\n\n");
////		String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//		String appSign = MD5Util.MD5Encode(sb.toString(), "").toUpperCase();
//		
////		Log.e("orion4",appSign);
//		return appSign;
//	}
	
	
	
	private String genAppSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constants.API_KEY);

//        this.sb.append("sign str\n"+sb.toString()+"\n\n");
//		String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		String appSign = MD5Util.MD5Encode(sb.toString(), "").toUpperCase();
//		
		
//		Log.e("orion4",appSign);
		return appSign;
	}

	
	
	
	
	
	public  WXPayBean  getWeiXinInfo(String id,String type,String biaoji,String totalFee){
		
		String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
		String	value=genProductArgs( id, type, biaoji, totalFee);
		
		
//         value="<xml><appid>wx1c164f6e8b1186bb</appid><attach>48,R,G</attach><body>æ¯ä»è¿è´¹</body><mch_id>1298695101</mch_id><nonce_str>c0172ea66506f59c8c435eb66176fb67</nonce_str><notify_url>http://115.28.58.245:80/chengszj/paySuccessWX.action</notify_url><out_trade_no>201601231844392097</out_trade_no><spbill_create_ip>127.0.0.1</spbill_create_ip><total_fee>1800</total_fee><trade_type>APP</trade_type><sign>D98CB94BFFF6493C23880AE58AB0C415</sign></xml>";
		String wxReturn=	SimpleHttpPost.response(url, value);
		log.debug("wxReturn="+wxReturn);
		WxInMessage xmlMap=new WxInMessage();
		xmlMap=WxInMessage.fromXml(wxReturn);
		WXPayBean wXPayBean=new WXPayBean();
		wXPayBean.setPrepayid(xmlMap.getValueByKey("prepay_id"));
		log.debug("prepay_id="+wXPayBean.getPrepayid());
		

		
		wXPayBean.setAppid(Constants.APP_ID);
		wXPayBean.setPartnerid(Constants.MCH_ID);
		wXPayBean.setPackages("Sign=WXPay");
		wXPayBean.setNoncestr(genNonceStr());
		wXPayBean.setTimestamp(timestamp);
		wXPayBean.setSign(getSign(xmlMap.getValueByKey("prepay_id")));
		return wXPayBean;
	}
	
	
}
