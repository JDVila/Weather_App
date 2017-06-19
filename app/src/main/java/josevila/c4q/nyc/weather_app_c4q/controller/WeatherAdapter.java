package josevila.c4q.nyc.weather_app_c4q.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import josevila.c4q.nyc.weather_app_c4q.R;
import josevila.c4q.nyc.weather_app_c4q.model.Period;
import josevila.c4q.nyc.weather_app_c4q.view.WeatherViewHolder;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<Period> periodList = new ArrayList<>();

    public WeatherAdapter(List<Period> periodList) {
        this.periodList = periodList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_itemview, parent, false);
        return new WeatherViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.onBind(periodList.get(position));
    }

    @Override
    public int getItemCount() {
        return periodList.size();
    }
}
