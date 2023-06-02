package com.example.futureweathermvp.Presenter;

import com.example.futureweathermvp.Entity.WeatherItem;

import java.util.List;

public interface WeatherPresenter {

    void getFutureWeather(String city);//获取天气数据方法

    void onWeatherDataFetched(List<WeatherItem> weatherItems);//天气数据回调方法


    void onWeatherDataFetchFailed(String message);
}
