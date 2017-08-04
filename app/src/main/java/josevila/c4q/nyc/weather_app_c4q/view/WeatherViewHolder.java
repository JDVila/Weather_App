package josevila.c4q.nyc.weather_app_c4q.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import josevila.c4q.nyc.weather_app_c4q.R;
import josevila.c4q.nyc.weather_app_c4q.model.Period;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private TextView date;
    private ImageView weatherImage;
    private TextView highTemp;
    private TextView lowTemp;
    private Map<String, Integer> pictureMap = new HashMap<>();

    public WeatherViewHolder(View itemView) {
        super(itemView);
        Field[] fields = R.drawable.class.getFields();
        for (int count = 0; count < fields.length; count++) {
            try {
                pictureMap.put(fields[count].getName(), fields[count].getInt(fields[count]));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        date = (TextView) itemView.findViewById(R.id.date_textview);
        weatherImage = (ImageView) itemView.findViewById(R.id.weather_imageview);
        highTemp = (TextView) itemView.findViewById(R.id.temp_high_textview);
        lowTemp = (TextView) itemView.findViewById(R.id.temp_low_textview);
    }

    public void onBind(Period period, boolean isClicked) {
        date.setText(period.dateTimeISO);
        weatherImage.setImageResource(pictureMap.get((period.icon).substring(0, (period.icon).length() - 4)));
        if (isClicked) {
            highTemp.setText("High: " + period.maxTempF + "°F");
            lowTemp.setText("Low: " + period.minTempF + "°F");
        } else {
            highTemp.setText("High: " + period.maxTempC + "°C");
            lowTemp.setText("Low: " + period.minTempC + "°C");
        }
    }
}