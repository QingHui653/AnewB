package newb.c.api.weather.retrofit;

import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
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
}
