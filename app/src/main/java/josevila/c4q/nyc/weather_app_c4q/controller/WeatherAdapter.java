package josevila.c4q.nyc.weather_app_c4q.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import josevila.c4q.nyc.weather_app_c4q.R;
import josevila.c4q.nyc.weather_app_c4q.model.Period;
import josevila.c4q.nyc.weather_app_c4q.view.WeatherViewHolder;

import static android.R.attr.button;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<Period> periodList = new ArrayList<>();
    private boolean isClicked;

    public WeatherAdapter(List<Period> periodList, boolean isClicked) {
        this.periodList = periodList;
        this.isClicked = isClicked;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_itemview, parent, false);
        return new WeatherViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.onBind(periodList.get(position), isClicked);
    }

    @Override
    public int getItemCount() {
        return periodList.size();
    }

}
