package josevila.c4q.nyc.weather_app_c4q;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import josevila.c4q.nyc.weather_app_c4q.backend.WeatherService;
import josevila.c4q.nyc.weather_app_c4q.controller.WeatherAdapter;
import josevila.c4q.nyc.weather_app_c4q.model.Period;
import josevila.c4q.nyc.weather_app_c4q.model.WeatherJson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.aerisapi.com/";
    //private static final String API_KEY = BuildConfig.API_KEY;
    private static final String CLIENT_ID = "QegbanSYW0jiHQ3qttYAl";
    private static final String CLIENT_SECRET = "8VMxIq0oCrIbVA4ZsJmsgq5NEOhiHrqH8URC07bK";
    private String TAG = "Did you connect? ";
    private boolean mIsSuccessful;
    private List<Period> weatherList = new ArrayList<>();
    private RecyclerView weatherRecyclerView;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherRecyclerView = (RecyclerView) findViewById(R.id.weather_recyclerview);
        connectToServer(BASE_URL);
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
                        weatherList = response.body().response.get(0).periods;
                        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true));
                        weatherRecyclerView.setAdapter(new WeatherAdapter(weatherList));
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
