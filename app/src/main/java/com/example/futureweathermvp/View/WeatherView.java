package com.example.futureweathermvp.View;

import com.example.futureweathermvp.Entity.WeatherItem;

import java.util.List;

public interface WeatherView {

    //更新视图
    void showFutureWeather(List<WeatherItem> weatherItems);

    //获取失败提示
    void showError(String message);

   // void setResultMessage(String inputString);

}
