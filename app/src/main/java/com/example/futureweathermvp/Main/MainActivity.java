package com.example.futureweathermvp.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futureweathermvp.Presenter.WeatherPresenter;
import com.example.futureweathermvp.Presenter.WeatherPresenterImpl;
import com.example.futureweathermvp.R;
import com.example.futureweathermvp.View.WeatherView;
import com.example.futureweathermvp.Entity.WeatherItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherView {

    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private WeatherPresenter presenter;

    private EditText mCityInput;

    //private TextView resultMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建一个WeatherPresenter实例
        presenter = new WeatherPresenterImpl(this);
        Button button=findViewById(R.id.searchButton);
        mCityInput = findViewById(R.id.cityInput);

      // resultMessageView=findViewById(R.id.resultMessage);

        recyclerView = findViewById(R.id.weatherRecyclerView);

       //设置点击事件监听
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取输入框中的查询字符串
                String queryString = mCityInput.getText().toString();

                //调用WeatherPresenter的getFutureWeather方法获取天气数据
                presenter.getFutureWeather(queryString);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void showFutureWeather(List<WeatherItem> weatherItems) {
        adapter.setWeatherItems(weatherItems);
    }

    @Override
    public void showError(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT);

    }


}