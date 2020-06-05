package newb.c.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    private final static String FORM_METHOD="FORM";
    private final static String JSON_METHOD="JSON";
    private final static String FILE_METHOD="FILE";

    private HttpUriRequest buildGetReq(String url, String param){
        return new HttpGet(url+"&"+param);
    }

    private Request buildGetHttp(String url, String param){
        return new Request.Builder().url(url+"&"+param).get().build();
    }

    private Request buildJsonPostHttp(String url, Map<String,Object> map){
        String data = JSON.toJSONString(map);
        RequestBody requestBody = FormBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),data);
        return new Request.Builder().url(url).post(requestBody).build();
    }

    private Request buildFormPostHttp(String url, Map<String,Object> map){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.add(entry.getKey(),(String) entry.getValue());
        }
        FormBody formBody = builder.build();
        return new Request.Builder().url(url).post(formBody).build();
    }

    private Request buildFilePostHttp(String url, Map<String,Object> map, File file){
        RequestBody fileBody = RequestBody.create(okhttp3.MediaType.parse("image/png"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.addFormDataPart(entry.getKey(),(String) entry.getValue());
        }
        MultipartBody multipartBody = builder.addFormDataPart("img", "fileName", fileBody).build();
        return new Request.Builder().url(url).post(multipartBody).build();
    }

    private HttpUriRequest buildJsonPostReq(String url, Map<String,Object> map){
        HttpPost httpPost = new HttpPost(url);
        String data = JSON.toJSONString(map);
        StringEntity entityParams = new StringEntity(data, "UTF-8");
        httpPost.setEntity(entityParams);
        httpPost.setHeader("Content-Type", "application/json;");
        return  httpPost;
    }

    private HttpUriRequest buildFormPostReq(String url,Map<String,Object> map) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(),(String) entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
        return  httpPost;
    }

    public String applyFormForString(String method, String url, String param,Map<String,Object> map) {
        return applyForString(method,FORM_METHOD,url, param, map,null);
    }

    public String applyForString(String method, String url, String param,Map<String,Object> map) {
        return applyForString(method,JSON_METHOD,url, param, map,null);
    }

    public String applyFileForString(String method, String url, String param,Map<String,Object> map,File file) {
        return applyForString(method,FILE_METHOD,url, param, map,file);
    }

    public String applyForStringByHttpClient(String method, String reqType, String url, String param,Map<String,Object> map) {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        String data = "";
        try {

            HttpUriRequest httpUriRequest;
            if("GET".equals(method)){
                httpUriRequest=buildGetReq(url,param);
            }else {
                if(FORM_METHOD.equals(reqType)) {
                    httpUriRequest = buildFormPostReq(url, map);
                }else {
                    httpUriRequest = buildJsonPostReq(url, map);
                }
            }

            client = HttpClients.createDefault();
            response = client.execute(httpUriRequest);
            if (response != null && response.getEntity() != null) {
                data = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            logger.error("[BaseYunPrintService.applyForString] Error: url:{}, param:{}", url, param);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("[BaseYunPrintService.applyForString] Close Response Error: url:{}, param:{}", url, param);
                }
            }

            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    logger.error("[BaseYunPrintService.applyForString] Close Client Error: url:{}, param:{}", url, param);
                }
            }
        }
        logger.info("[BaseYunPrintService.postForJson] data:{}", data);
        return data;
    }

    public String applyForString(String method, String reqType, String url, String param,Map<String,Object> map,File file) {

        String data = "";
        okhttp3.Response response = null;
        try{
            Request request;
            if("GET".equals(method)){
                request=buildGetHttp(url,param);
            }else {
                if(FORM_METHOD.equals(reqType)) {
                    request = buildFormPostHttp(url, map);
                }else if (FILE_METHOD.equals(reqType) && file !=null ){
                    request = buildFilePostHttp(url,map,file);
                }else {
                    request = buildJsonPostHttp(url, map);
                }
            }
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            Call call = okHttpClient.newCall(request);
            response = call.execute();
            if(response.isSuccessful()){
                data = response.body().string();
            }
        }catch (Exception e){
            logger.error("[BaseYunPrintService.applyForString] Error: url:{}, param:{}", url, param);
        }finally {
            if (response != null) {
                response.close();
            }
        }
        logger.info("[BaseYunPrintService.postForJson] data:{}", data);
        return data;
    }
}
