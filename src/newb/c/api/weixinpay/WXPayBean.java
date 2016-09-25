package newb.c.api.weixinpay;
import org.apache.http.message.BasicNameValuePair;

public class WXPayBean {

	
//	signParams.add(new BasicNameValuePair("appid", req.appId));
//	signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
//	signParams.add(new BasicNameValuePair("package", req.packageValue));
//	signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
//	signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
//	signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
	
	
	private String appid;
	private String noncestr;
	private String packages;
	private String partnerid;
	private String prepayid;
	private String timestamp;
	private String sign;
	
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
