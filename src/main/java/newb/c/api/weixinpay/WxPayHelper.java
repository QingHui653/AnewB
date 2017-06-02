package newb.c.api.weixinpay;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import newb.c.util.SimpleHttpPost;

public class WxPayHelper {
    private static final String WX_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private static Logger logger = Logger.getLogger(WxPayHelper.class);

    /**
     * 微信统一支付
     *
     * @return
     */
    public static String unifiedorder(Map<String, Object> payMap, String paykey) {
        //对参数进行排序，不编码
        String sortStr = CommonUtil.formatParaMap(payMap, false);
        //在string后加入KEY
        String signStr = sortStr + "&key=" + paykey;
        //MD5加密
        String sign = MD5Util.MD5Encode(signStr, "utf-8").toUpperCase();
        //加入统一支付参数中
        payMap.put("sign", sign);
        //得到统一支付接口请求xml
        String xml = CommonUtil.mapToXml(payMap);

        String prepay_id = null;
        try {
            String ret = SimpleHttpPost.response(WX_PAY_URL, xml);
            logger.debug("unifiedorder ret =" + ret);
            if (StringUtils.isNotEmpty(ret)) {
                Map<String, Object> retMap = CommonUtil.xmlToMap(ret);
                if ("SUCCESS".equals(retMap.get("return_code"))) {
                    if ("SUCCESS".equals(retMap.get("result_code"))) {
                        prepay_id = (String) retMap.get("prepay_id"); //有效期为2小时
                    }
                }
            }
        } catch (Exception e) {
            logger.error("调用统一下单接口出错", e);
        }
        return prepay_id;
    }

    public static String signMap(Map<String, Object> signMap, String appSecret) {
        //对参数进行排序，不编码
        String sortStr = CommonUtil.formatParaMap(signMap, false);
        //在string后加入KEY
        String signStr = sortStr + "&key=" + appSecret;
//        logger.debug("signStr==" + signStr);
        String sign = MD5Util.MD5Encode(signStr, "utf-8").toUpperCase();
//        logger.debug("sign==" + signStr);
        return sign;
    }

    public static void main(String[] args) {
//        String s = "attach=重要嘉宾报名费10元&bank_type=WX&body=自定打印发布会报名费&fee_type=1&input_charset=UTF-8&notify_url=http://u01.oboard.net/meeting/h5/notify&out_trade_no=551286e9af6555b9d0136779&partner=1219011801&spbill_create_ip=192.168.1.207&total_fee=1000&key=weipass2505xulingyun198512102505";
//        System.out.println(s);
//        String sign = MD5.sign(s).toUpperCase();
//        System.out.println("main==" + sign);

        String s1 = "appId=wx358c1830fe64c3a4&nonceStr=pxwfayo53rjbmt5k&package=prepay_id=wx2015032616382494235e0ede0955163397&signType=MD5&timeStamp=1427359100&key=uanbaod2014dyuanbaod2015dyuanbao";
        System.out.println(MD5Util.MD5Encode(s1,"").toUpperCase());
        
        
        
//        //构建统一下单参数
//        Map<String,Object> paraMap = new HashMap<String, Object>();
//        paraMap.put("appid", WxPayConfig.appId);
//        paraMap.put("mch_id",WxPayConfig.mchid2);
//        paraMap.put("nonce_str",CommonUtil.createNoncestr());
//        paraMap.put("body","元宝会员服务");
//        paraMap.put("out_trade_no", obj.get("_id"));
//        paraMap.put("total_fee",money * 100);
//        paraMap.put("spbill_create_ip","127.0.0.1");
//        paraMap.put("notify_url",WxPayConfig.notify_url);
//        paraMap.put("trade_type","APP");
//
//        String prepay_id = null; //预支付id
//        for(int i = 0; i <= 3; i++){
//            prepay_id = WxPayHelper.unifiedorder(paraMap, WxPayConfig.paykey);
//            if(StringUtils.isNotEmpty(prepay_id)){
//                break;
//            }
//        }
//        logger.info("====prepay_id====" + prepay_id);
//        DBObject payData = new BasicDBObject();
//        if(StringUtils.isNotEmpty(prepay_id)){
//            //签名参数
//            HashMap<String, Object> payMap = new HashMap<String, Object>();
//            payMap.put("appid", WxPayConfig.appId);
//            payMap.put("partnerid",WxPayConfig.mchid2);
//            payMap.put("prepayid",prepay_id);
//            payMap.put("package","Sign=WXPay");
//            payMap.put("noncestr", CommonUtil.createNoncestr());
//            payMap.put("timestamp",CommonUtil.getTimeStamp());
//
//            //签名
//            String sign = WxPayHelper.signMap(payMap,WxPayConfig.paykey);
//            payMap.put("sign",sign);
//
//            //装入js支付数据
//            payData.putAll(payMap);
//        }
    }

}
