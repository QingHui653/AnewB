package newb.c.a_spring.api.weather.openapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitOpenApiTest {

    private Gson gson = new Gson();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.239:8088/OpenApi/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private OpenApiService service =retrofit.create(OpenApiService.class);

    @Test
    public void getWeatherTest() throws IOException {
        Call<JsonObject> call = service.createrByApi("SY170504000001", "SY170504000001","{\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"supplierCompany\": \"范德萨\",\n" +
                "            \"systemId\": \"fds13\",\n" +
                "            \"saleMode\": \"常规订单\",\n" +
                "            \"deliveryDate\": \"2018-03-10\",\n" +
                "            \"contactName\": \"1\",\n" +
                "            \"contactMobil\": \"1\",\n" +
                "            \"address\": \"1\",\n" +
                "            \"remark\": \"第二条订单\",\n" +
                "            \"orderSkuList\": [\n" +
                "                {\n" +
                "                    \"skuCode\": \"SP170801000001\",\n" +
                "                    \"barCode\": \"298719869\",\n" +
                "                    \"purchaseNum\": 1,\n" +
                "                    \"remark\": \"x1\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"supplierCompany\": \"范德萨\",\n" +
                "            \"systemId\": \"fds1\",\n" +
                "            \"saleMode\": \"常规订单\",\n" +
                "            \"deliveryDate\": \"2018-03-10\",\n" +
                "            \"contactName\": \"1\",\n" +
                "            \"contactMobil\": \"1\",\n" +
                "            \"address\": \"1\",\n" +
                "            \"remark\": \"第二条订单\",\n" +
                "            \"orderSkuList\": [\n" +
                "                {\n" +
                "                    \"skuCode\": \"SP170801000001\",\n" +
                "                    \"barCode\": \"298719869\",\n" +
                "                    \"purchaseNum\": 2,\n" +
                "                    \"remark\": \"x2\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        Response<JsonObject> a =call.execute();
        if(a.isSuccessful()){
            JsonObject json = a.body();
            System.out.println(json.toString());
        }else {
            System.out.println(a.errorBody().string());
        }
    }

}

