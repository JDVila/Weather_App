package josevila.c4q.nyc.weather_app_c4q;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import josevila.c4q.nyc.weather_app_c4q.backend.WeatherClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherClient.getInstance().pullWeather();
    }
}
