package newb.c.a_spring.api.weather.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitWeather {
    //配置您申请的KEY
    public static final String APPKEY ="847d63a6595c2b0933d322bd0d310aaa";

    public Gson gson = new Gson();

    public String queryWeather(String cityname) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/onebox/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WeatherService service =retrofit.create(WeatherService.class);

        Call<JsonObject> call = service.getWeatherByForm(cityname, APPKEY);
        Response<JsonObject> a =call.execute();

        if(a.isSuccessful()){
            JsonObject json = a.body();
            return json.toString();
        }else {
            return a.errorBody().string();
        }
    }

}

