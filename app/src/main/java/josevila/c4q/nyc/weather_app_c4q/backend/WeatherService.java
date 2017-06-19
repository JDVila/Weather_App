package josevila.c4q.nyc.weather_app_c4q.backend;

import josevila.c4q.nyc.weather_app_c4q.model.WeatherJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("forecasts/11101")
    Call<WeatherJson> getData(@Query("client_id") String clientID, @Query("client_secret") String clientSecret);

}
