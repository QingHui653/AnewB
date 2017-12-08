package newb.c.a_spring.api.weather.retrofit;

import com.google.gson.JsonObject;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface WeatherService {

    @FormUrlEncoded
    @POST("weather/query")
    Call<JsonObject> getWeatherByForm(@Field("cityname") String cityName, @Field("key") String key);

    @GET("weather/query")
    Call<JsonObject> getWeatherByGet(@Query("cityname") String cityName, @Query("key") String key);

    @POST("weather/query")
    Call<JsonObject> getWeatherByPost(@Query("cityname") String cityName, @Query("key") String key);

    @POST("weather/query")
    //RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),body);
    Call<JsonObject> getWeatherByRePost(@Body RequestBody body);
}
