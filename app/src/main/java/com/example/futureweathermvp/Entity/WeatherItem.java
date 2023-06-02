package com.example.futureweathermvp.Entity;

public class WeatherItem {
    private String date;
    private String temperature;
    private String weather;

    private String backResult;


    public WeatherItem(String date, String temperature, String weather) {
        this.date = date;
        this.temperature = temperature;
        this.weather = weather;

    }

    public  WeatherItem(String backResult){

        this.backResult=backResult;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getBackResult() {
        return backResult;
    }

    public void setBackResult(String backResult) {
        this.backResult = backResult;
    }
}
