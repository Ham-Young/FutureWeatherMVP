package com.example.futureweathermvp.Presenter;

import com.example.futureweathermvp.Entity.WeatherItem;
import com.example.futureweathermvp.Model.WeatherModel;
import com.example.futureweathermvp.Model.WeatherModelImpl;
import com.example.futureweathermvp.View.WeatherView;

import java.util.List;

public class WeatherPresenterImpl implements WeatherPresenter {

    private WeatherModel model;
    private WeatherView view;


    public WeatherPresenterImpl(WeatherView view) {
        this.view = view;
        this.model = new WeatherModelImpl(this);
    }


    //通过传递city参数，调用WeatherModel的fetchWeatherDataFromApi方法
    @Override
    public void getFutureWeather(String city) {
        model.fetchWeatherDataFromApi(city);
    }

    //请求数据成功的回调方法
    @Override
    public void onWeatherDataFetched(List<WeatherItem> weatherItems) {

        if (weatherItems != null && !weatherItems.isEmpty()) {
            //将weatherItems回调给WeatherView的showFutureWeather方法
            view.showFutureWeather(weatherItems);
        } else {
            view.showError("weatherItems为空");
        }

    }


    //请求数据失败的回调方法
    @Override
    public void onWeatherDataFetchFailed(String errorMessage) {

          // 处理数据获取失败情况，更新视图
        view.showError(errorMessage);


    }
}
