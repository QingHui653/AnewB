package newb.c.api.alipay.config.chongzhi;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	
	//cszj  
	public static String partner = "2088911953457367";
	//public static String partner = "2088021882880542";//众人帮
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	public static String seller_id =partner;
	// 商户的私钥
	//生成文档 https://help.alipay.com/support/help_detail.htm?help_id=397433&sh=Y&tab=null&info_type=9
	//cszj 
	public static String private_key ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMKbfAILBM2CDJcYvhDuD0N4TCF2Gqql6idQ4mf3zZQ2xuGq1W4TrH3GP4BR7lZOHSsadjzFaRzPD8vn6p0ixHh03PfaKD4ejqiZ/NXzUF3Mg9GQnMM7rei8Ii97ulx4XzxAmRjxa31lyLbSltnJ8UgD0PtIvo7YaYmcNK8IRRMpAgMBAAECgYAnSRgZmXvYuL4t6nLEHCq8phZIUv1DIcdJNIzgDChWGVDA0anQVOxnX5QGJYQFrDM7NlZUIk374NkBgsnb+t7KT88KVQHOqJPXHl+/O+U2u092Iq2vsERcW8N2/NdMJENM3Ucw3x/muGTXccNgs/k8mL9ESazimPXIOY9u27+/AQJBAPZOgKgai9G4MN9pVXroQ65RX+lmWEM2k0we7JK2EVgcDwdr5MN0PbkHjZxZ5Ln4PN4prUkwaO0S+zg2ebsFWvECQQDKRB7N0WiRwK99wwhuGpLt0YLpHzrAWa1wU2T10ih1m0ZKKi6tlU1ebzKar+z8ldg34kNqOm9WBsXSVUZiXgu5AkAK5xyw1EP+K44WRZ5gC2n7jGSXe01K5F6OzwufzuljohJUPMVqlXmp+BifpCcIyGFBYEzoYXkzjVSLf3XR/oGxAkBXNZHWiiC9H0hLQClFV93b/kakfxuUzdWzJbHVL5zUQHPJDHD91O779ZPrEEltCqqkRcFtbu6MN/VZX/0t8oC5AkAg+jkB/IaIJbVW9M0phbRFmQROj4qTSc0gI2qKPvlmXt4Sy3vi3iZe1o14ms8DbWbKkA5DI89bVeiUGSOC9eH2";
	//众人帮	 //public static String private_key ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOTnCsoBdANn+OKw3BeglgEWuaM0S9wUUgdAf8ESfA3LhXVYgh59p45vLlA03r1s6MJKYPxZJgsNTB+k07kM7C7kNJ8BbD1zjZeiN/1q5Xi99arfELw4szciv7ETb5LkmrW91sj/edME2Dny4L5alVv46keGoUZZllVXkjU7uKbjAgMBAAECgYBPRjj1VuV/3H1UvR2sTSBGRkGKZS00jhEOk+bqzeUKvgZ9ama9+pSR/q1Sd80AW3yM9Ud7fcBwKW3Is8PjnbcZqmyOOUdi+Or9R9nOm+96NmxuH3QcHHZ1K72nHg7ZS2bQ2t5hidJkBUPqhTnfw3GkCHrOq1fSdQsA+BEm83QngQJBAP5OAqZ8rx8/oNdkAiXk/zbEs7aVuIMpxx2BDe39m/SMSqlkK15RL+coZnhDSrloXt4kup/+ovBxZfC0aqKlIcECQQDmba5Y3WTXdRx9FKw9hdvZNIA7wPXrcbXs9oJwu6hjRTKWOLRjaCmTu9oZIEEvq5zniGqZhr9feROYbM8ovGmjAkAh8u4dpbWHOWpSdtJmlqyPdjD7pf8s223ufWULqXiha/0noey8OaBofpc3ZNxb5hgMFeEoLMk5yhmuyZcWwW7BAkEA1mfv9hfqoOADZg86RPSsn+pN90uQh0cC9D5bGPJDuDrG2yhgEK73INOGGCL/BJA2Kumv5rKOlxNdvFJiX5EQ6QJAdLiVHs5aTkPKNNL+hnigyJNJOZ494Lwu+TMEMLMVXnCUhbK/6C+l1ztknuQ0Ljhay9xlm0U3xuZDaKb7dlHJQg==";
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\testzhifubao.text";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
