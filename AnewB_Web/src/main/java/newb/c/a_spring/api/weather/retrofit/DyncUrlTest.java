package newb.c.a_spring.api.weather.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import newb.c.a_spring.api.weixinpay.MD5Util;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.hadoop.hdfs.util.MD5FileUtils;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class DyncUrlTest {

    public Gson gson = new Gson();


    public String sendScf() throws IOException {

        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");

        Retrofit retrofit = new Retrofit.Builder()
                // 此链接必须 存在
                .baseUrl("http://linkb2b.com.cn/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ScfService service =retrofit.create(ScfService.class);

        String body = "{\n" +
                "        \"srcAppFlag\":\"scm\",\n" +
                "        \"srcAppTime\":\"来源系统创建时间\",\n" +
                "        \"destAppFlag\":\"目标系统标志\",\n" +
                "        \"loanId\":\"融资单ID\",\n" +
                "        \"ordersId\":\"订单ID\",\n" +
                "        \"inspectionCode\":\"验货单号\",\n" +
                "        \"inspectionStatus\":1,\n" +
                "        \"notifierId\":\"通知验货人\",\n" +
                "        \"notifierCompanyId\":\"通知验货人所属公司ID\",\n" +
                "        \"notifieTime\":\"通知验货日期\",\n" +
                "        \"inspectPlanTime\":\"计划验货时间\",\n" +
                "        \"inspectActualTime\":\"实际验货时间\",\n" +
                "        \"inspectionCompany\":\"验货执行公司名称\",\n" +
                "        \"inspectionExecutor\":\"验货执行人名称\",\n" +
                "        \"inspectionExecutorPhone\":\"验货执行人联系电话\",\n" +
                "        \"auditDealerUser\":\"融资方验货审核人\",\n" +
                "        \"auditDealerTime\":\"融资方验货审核日期\",\n" +
                "        \"auditDealerOpinion\":\"融资方验货审核意见\",\n" +
                "        \"auditSupplierUser\":\"资金方验货审核人\",\n" +
                "        \"auditSupplierTime\":\"资金方验货审核日期\",\n" +
                "        \"auditSupplierOpinion\":\"资金方验货审核意见\",\n" +
                "        \"createDate\":\"创建时间\",\n" +
                "        \"remark\":\"备注\",\n" +
                "        \"filesList\": [\n" +
                "            {\n" +
                "                \"fileName\":\"附件名称\",\n" +
                "                \"fileUrl\":\"附件地址\",\n" +
                "                \"fileType\":\"txt\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        String md5 = MD5Util.MD5Encode(body,"UTF8");

        System.out.println(md5);//1a7b1820f32579e3206fdc83f3358dcb
        String appId="SCMB2B";
        String companyCode="HYDT";
        String requestId=md5;

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),body);

//        Call<ResponseBody> call =  service.call("http://ehubapi-test.linkb2b.com.cn/financial/scf/inspectionConfirm/OCP_CN",appId,companyCode,requestId,requestBody);
        Call<ResponseBody> call =  service.call("http://dev.scfb2b.com.cn/api-scmb2b/bizGoodsReleaseResult/confirm",appId,companyCode,requestId,requestBody);
        Response<ResponseBody> a =call.execute();

        if(a.isSuccessful()){
            System.out.println(body);
            System.out.println(a.body().string());
            return null;
        }else {
            return a.errorBody().string();
        }
    }

    @Test
    public void getWeatherTest() throws IOException {
        String res = sendScf();
        System.out.println(res);
    }
}
