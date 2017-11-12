package newb.c.a_spring.api.weather.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitWeatherTest {

    private String key = "847d63a6595c2b0933d322bd0d310aaa";
    private Gson gson = new Gson();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://op.juhe.cn/onebox/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private WeatherService service =retrofit.create(WeatherService.class);

    @Test
    public void getWeatherTest() throws IOException {
        Call<JsonObject> call = service.getWeatherByForm("广州", key);
        Response<JsonObject> a =call.execute();
        if(a.isSuccessful()){
            JsonObject json = a.body();
            System.out.println(json.toString());
        }else {
            System.out.println(a.errorBody().string());
        }
    }

    @Test
    public void getWeatherByGetTest() throws IOException {
        Call<JsonObject> call = service.getWeatherByGet("广州", key);
        Response<JsonObject> a =call.execute();

        if(a.isSuccessful()){
            JsonObject json = a.body();
            System.out.println(json.toString());
        }else {
            System.out.println(a.errorBody().string());
        }
    }

    @Test
    public void getWeatherByPostTest() throws IOException {
        Call<JsonObject> call = service.getWeatherByPost("广州", key);
        Response<JsonObject> a =call.execute();

        if(a.isSuccessful()){
            JsonObject json = a.body();
            System.out.println(json.toString());
        }else {
            System.out.println(a.errorBody().string());
        }
    }
}

