package com.example.futureweathermvp.Model;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.futureweathermvp.Presenter.WeatherPresenter;
import com.example.futureweathermvp.Entity.WeatherItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherModelImpl implements WeatherModel {

    private static final String BASE_URL ="https://apis.juhe.cn/simpleWeather/query?";
    private static final String KEY = "0af448c7d41f6ec2fb12059994cda518";
    private WeatherPresenter presenter;
    private Handler handler;
    private String ResultMessage;


    public WeatherModelImpl(WeatherPresenter presenter) {

        this.presenter = presenter;
        handler = new Handler(Looper.getMainLooper());

    }


    //通过网络请求获取jason数据，并回调给presenter
    @Override
    public void fetchWeatherDataFromApi( String city) {


        // 构建完整的 URL，将城市名称和 API Key 添加到查询参数中
        HttpUrl url = HttpUrl.parse(BASE_URL)
                .newBuilder()
                .addQueryParameter("city", city) // 添加城市名称的查询参数
                .addQueryParameter("key", KEY) // 添加 API Key 的查询参数
                .build();

        OkHttpClient myClient=new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).
                get().
                build();

        //发起异步请求
        myClient.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(@NonNull Call call, @NonNull IOException e) {
               e.printStackTrace();
               Log.e("get异步响应失败" , e.toString());
               //请求失败，处理失败情况,主线程更新UI
             handler.post(new Runnable() {
                 @Override
                 public void run() {
                     presenter.onWeatherDataFetchFailed(e.getMessage());
                 }
             });

           }

           @Override
           public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
               if (response.isSuccessful()) {
                   // 请求成功，处理响应数据
                   String jsonData = response.body().string();
                   // 解析 JSON 数据，获取天气信息
                   List<WeatherItem> weatherItems = parseJsonData(jsonData);

                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           //将天气数据回调给 Presenter 对象
                           presenter.onWeatherDataFetched(weatherItems);
                       }
                   });

               } else {
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           // 处理网络请求失败的情况
                           // 可以通过回调接口将错误信息传递给 Presenter 进行处理
                           presenter.onWeatherDataFetchFailed("Network request failed with code: " + response.code());
                       }
                   });

               }


           }
       });
    }

    //解析Jason数据的parseJsonData方法
    private List<WeatherItem> parseJsonData(String jsonData) {



        List<WeatherItem> weatherItems = new ArrayList<>();

        try {

            //将字符串转化为jason对象
            JSONObject jsonObject = new JSONObject(jsonData);

            //获取查询结果提示信息
            ResultMessage = jsonObject.getString("reason");
               
            if (!ResultMessage.equals("查询成功!")&&!ResultMessage.equals("查询成功")){

                //创建weatherItem1对象
                WeatherItem weatherItem1 = new WeatherItem(ResultMessage);
                //将带有返回结果信息的weatherItem1对象添加到list
                weatherItems.add(weatherItem1);
            } else{

                ////从jason对象中获取jsonObject的子对象resultObject
                JSONObject resultObject = jsonObject.getJSONObject("result");
                //从resultObject对象中获取名为futureArray的jasonArray
                JSONArray futureArray = resultObject.getJSONArray("future");

                //遍历futureArray，并获取到date，temperature，weather

                for (int i = 0; i < futureArray.length(); i++) {
                    JSONObject itemObject = futureArray.getJSONObject(i);
                    String date = itemObject.getString("date");
                    String temperature = itemObject.getString("temperature");
                    String weather = itemObject.getString("weather");
                    //创建WeatherItem2对象
                    WeatherItem weatherItem2 = new WeatherItem(date, temperature, weather);
                    //将WeatherItem对象添加到list
                    weatherItems.add(weatherItem2);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherItems;
    }



}

