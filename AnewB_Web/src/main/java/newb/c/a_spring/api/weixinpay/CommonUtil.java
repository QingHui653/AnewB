package newb.c.a_spring.api.weixinpay;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 微信支付工具类
 */
public class CommonUtil {
    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTimeStamp() {
        Calendar cl = Calendar.getInstance();
        return Long.toString(cl.getTimeInMillis() / 1000);
    }


    /**
     * 生成指定长度的随机数
     *
     * @param length 随机数长度
     * @return
     */
    public static String createNoncestr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        String res = "";
        for (int i = 0; i < length; i++) {
            Random rd = new Random();
            res += chars.indexOf(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 生成16位长的随机数
     *
     * @return
     */
    public static String createNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 取得客户端ip
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * 按字节算字符串长度
     *
     * @param s 要测试的字符串
     * @return
     */
    public static int getWordCount(String s) {
        if (s == null) {
            return -1;
        }
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 参数校验，V2版本使用
     *
     * @param paraMap 参数
     * @return
     */
    public boolean checkPackage(Map<String, Object> paraMap) {
        boolean ret = true;
        //必填
        String body = (String) paraMap.get("body"); //商品描述
        //商户订单号，商户系统内部的订单号， 32 个字符内、可包含字母，确保在商户系统唯一
        String out_trade_no = (String) paraMap.get("out_trade_no");
        String total_fee = (String) paraMap.get("total_fee"); //订单总金额，订单总金额，单位为分
        //通知 URL，在支付完成后，接收微信通知支付结果的 URL， 需给绝对路径， 255 字符内
        String notify_url = (String) paraMap.get("notify_url");
        //订单生成的机器 IP，指用户浏览器端 IP，丌是商户服务器 IP，格式为IPV4
        String spbill_create_ip = (String) paraMap.get("spbill_create_ip");
        //可选
        String attach = (String) paraMap.get("attach"); //附加数据，128 字节以下
        //交易起始时间，订单生成时间，格式为 yyyyMMddHHmmss
        String time_start = (String) paraMap.get("time_start");
        //交易结束时间，订单失效时间，格式为 yyyyMMddHHmmss
        String time_expire = (String) paraMap.get("time_expire");
        //物流费用，单位为分。如果有值，必须保证 transport_fee + product_fee=total_fee
        String transport_fee = (String) paraMap.get("transport_fee");
        //商品费用，单位为分。如果有值，必须保证 transport_fee + product_fee=total_fee
        String product_fee = (String) paraMap.get("product_fee");

        if (StringUtils.isEmpty(body) || getWordCount(body) > 128) {
            ret = false;
        } else if (StringUtils.isEmpty(out_trade_no) || getWordCount(out_trade_no) > 32) {
            ret = false;
        } else if (StringUtils.isEmpty(total_fee)) {
            ret = false;
        } else if (StringUtils.isEmpty(notify_url) || getWordCount(notify_url) > 255) {
            ret = false;
        } else if (StringUtils.isEmpty(spbill_create_ip) || getWordCount(spbill_create_ip) > 15) {
            ret = false;
        } else if (StringUtils.isNotEmpty(attach) && getWordCount(attach) > 128) {
            ret = false;
        } else if (StringUtils.isNotEmpty(time_start) && getWordCount(time_start) != 14) {
            ret = false;
        } else if (StringUtils.isNotEmpty(time_expire) && getWordCount(time_expire) != 14) {
            ret = false;
        }
        return ret;
    }

    /**
     * 对参数进行排序、编码，返回格式化好的字符串
     *
     * @param paraMap   参数
     * @param urlencode 是否需要对参数编码
     * @return
     * @throws RuntimeException
     */
    public static String formatParaMap(Map<String, Object> paraMap, boolean urlencode) throws RuntimeException {
        String buff = "";
        try {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(paraMap.entrySet());

            Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
                @Override
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            for (int i = 0; i < infoIds.size(); i++) {
                Map.Entry<String, Object> item = infoIds.get(i);
                if (item.getKey() != "") {
                    String key = item.getKey();
                    Object val = item.getValue();
                    if (urlencode) {
                        val = URLEncoder.encode(val.toString(), "utf-8");
                    }
                    buff += key + "=" + val + "&";
                }
            }

            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return buff;
    }

    public static String arrayToXml(HashMap<String, Object> arr) {
        String xml = "<xml>";

        Iterator<Map.Entry<String, Object>> iter = arr.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            String key = entry.getKey();
            Object val = entry.getValue();
            if (val instanceof Integer) {
                xml += "<" + key + ">" + val + "</" + key + ">";
            } else {
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
            }
        }

        xml += "</xml>";
        return xml;
    }

    public static String mapToXml(Map<String, Object> arr) {
        String xml = "<xml>";
        Iterator<Map.Entry<String, Object>> iter = arr.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            String key = entry.getKey();
            xml += "<" + key + ">" + entry.getValue() + "</" + key + ">";
        }

        xml += "</xml>";
        return xml;
    }

    public static Map<String, Object> xmlToMap(String xml) throws Exception {
        Map map = new HashMap();
        Document document = DocumentHelper.parseText(xml);
        Element nodeElement = document.getRootElement();
        List node = nodeElement.elements();
        for (Iterator it = node.iterator(); it.hasNext(); ) {
            Element elm = (Element) it.next();
            map.put(elm.getName(), elm.getText());
        }
        return map;
    }

//    public static Map<String, String> getHashMapByDbObject(DBObject dbo) {
//        Map<String, String> p = new HashMap<String, String>();
//        for (String key : dbo.keySet()) {
//            Object value = dbo.get(key);
//            if (value == null) {
//                continue;
//            }
//            p.put(key, value.toString());
//        }
//        return p;
//    }

//    public static DBObject getDbObjectByHashMap(Map<String, String> map) {
//        DBObject dbo = new BasicDBObject();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            dbo.put(entry.getKey(), entry.getValue());
//        }
//        return dbo;
//    }
//
//    public static DBObject getDBObject(String xml) throws Exception {
//        return getDBObject(new ByteArrayInputStream(xml.getBytes()));
//    }

//    public static DBObject getDBObject(InputStream is) throws Exception {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db;
//        org.w3c.dom.Document doc;
//        BasicDBObject rs = null;
//        try {
//            db = dbf.newDocumentBuilder();
//            doc = db.parse(is);
//            org.w3c.dom.Element root = doc.getDocumentElement();
//            NodeList ns = root.getChildNodes();
//            rs = new BasicDBObject();
//            for (int i = 0; i < ns.getLength(); i++) {
//                String key = ns.item(i).getNodeName();
//                String value = ns.item(i).getTextContent();
//                if (org.springframework.util.StringUtils.isEmpty(key) || org.springframework.util.StringUtils.isEmpty(value) || "#text".equals(key)){
//                    continue;
//                }
//                rs.put(key, value);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (is != null)
//                is.close();
//        }
//        return rs;
//    }

    public static String notify(boolean success) {
        StringBuilder xml = new StringBuilder("<xml><return_code><![CDATA[");
        try {
            xml = new StringBuilder("<xml><return_code><![CDATA[");
            xml.append(success ? "SUCCESS" : "FAIL");
            xml.append("]]></return_code></xml>");
            return xml.toString();
        } finally {
            xml.setLength(0);
        }
    }

    public static void main(String[] args) throws Exception {
        String xml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>";
        System.out.println(xmlToMap(xml));
    }

}
