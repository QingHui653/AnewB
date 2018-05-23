package newb.c.a_spring.api.weather.openapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitOpenApi {

    public Gson gson = new Gson();

    public String queryWeather(String data) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.239:8088/OpenApi/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        OpenApiService service =retrofit.create(OpenApiService.class);

        Call<JsonObject> call = service.createrByApi("SY170504000001", "SY170504000001",data);
        Response<JsonObject> a =call.execute();

        if(a.isSuccessful()){
            JsonObject json = a.body();
            return json.toString();
        }else {
            return a.errorBody().string();
        }
    }

}

