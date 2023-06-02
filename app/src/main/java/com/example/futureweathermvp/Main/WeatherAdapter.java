package com.example.futureweathermvp.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futureweathermvp.R;
import com.example.futureweathermvp.Entity.WeatherItem;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherItem> weatherItems;

    public void setWeatherItems(List<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherItem weatherItem = weatherItems.get(position);
        holder.resultTextView.setText(weatherItem.getBackResult());
        holder.dateTextView.setText(weatherItem.getDate());
        holder.temperatureTextView.setText(weatherItem.getTemperature());
        holder.weatherTextView.setText(weatherItem.getWeather());
    }

    @Override
    public int getItemCount() {
        return weatherItems != null ? weatherItems.size() : 0;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView resultTextView;
        private TextView dateTextView;
        private TextView temperatureTextView;
        private TextView weatherTextView;


        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            resultTextView = itemView.findViewById(R.id.resultText);
            dateTextView = itemView.findViewById(R.id.dateText);
            temperatureTextView = itemView.findViewById(R.id.temperatureText);
            weatherTextView = itemView.findViewById(R.id.weatherText);

        }


    }
}

