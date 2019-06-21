package newb.c.a_spring.api.weather.retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ScfService {

    @POST()
    public Call<ResponseBody> call(@Url String url,@Query("appId") String appId, @Query("companyCode") String companyCode,@Query("requestId") String requestId, @Body RequestBody body);
}
