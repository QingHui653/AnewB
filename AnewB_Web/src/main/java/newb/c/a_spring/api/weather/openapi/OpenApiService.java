package newb.c.a_spring.api.weather.openapi;

import com.google.gson.JsonObject;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface OpenApiService {

    @FormUrlEncoded
    @POST("purchase/orders/createNew")
    Call<JsonObject> createrByApi(@Field("app_id") String app_id, @Field("company_id") String company_id, @Field("data") String data);

}