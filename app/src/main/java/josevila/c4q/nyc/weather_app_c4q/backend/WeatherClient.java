package josevila.c4q.nyc.weather_app_c4q.backend;

import android.util.Log;

import java.io.IOException;

import josevila.c4q.nyc.weather_app_c4q.model.WeatherJson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    private static final String BASE_URL = "https://api.aerisapi.com/";
    //private static final String API_KEY = BuildConfig.API_KEY;
    private static final String CLIENT_ID = "QegbanSYW0jiHQ3qttYAl";
    private static final String CLIENT_SECRET = "8VMxIq0oCrIbVA4ZsJmsgq5NEOhiHrqH8URC07bK";
    private Response<WeatherJson> mWeatherResponse;
    private String TAG = "Did you connect? ";
    private boolean mIsSuccessful;
    private static WeatherClient mClient = new WeatherClient();

    private WeatherClient() {
        //Private constructor!!!
    }

    public static WeatherClient getInstance() {
        return mClient;
    }

    public void pullWeather() {
        connectToServer(BASE_URL);
    }

    public boolean wasSuccess() {
        return mIsSuccessful;
    }

    private void connectToServer(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherJson> call = service.getData(CLIENT_ID, CLIENT_SECRET);
        call.enqueue(new Callback<WeatherJson>() {

            @Override
            public void onResponse(Call<WeatherJson> call, Response<WeatherJson> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "Yes you did! " + response.body());
                        mIsSuccessful = true;
                        mWeatherResponse = response;
                    } else {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure (Call <WeatherJson> call, Throwable t){
                Log.d(TAG, "Oh No you did not. " + t.getMessage());
            }
        });
    }
}
