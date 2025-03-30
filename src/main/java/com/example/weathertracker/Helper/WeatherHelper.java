package com.example.weathertracker.Helper;

import com.example.weathertracker.entity.Weather;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherHelper {
    String urlFormat = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";
    @Value("${weather.api.key}")
    private String apiKey;
    @Autowired RequestHelper requestHelper;

    public Weather getWeatherInfoFromOpenWeather(String latitude, String longitude) throws IOException, InterruptedException {
        String url = String.format(urlFormat, latitude, longitude, apiKey);
        JSONObject responseObject = requestHelper.getResponse(url,apiKey);
        JSONObject mainStats = responseObject.getJSONObject("main");
        String windSpeed = responseObject.getJSONObject("wind").get("speed").toString();
        Weather weather = new Weather();
        weather.setWindSpeed(windSpeed);
        weather.setHighTemparature(mainStats.get("temp_max").toString());
        weather.setLowTemparature(mainStats.get("temp_min").toString());
        weather.setPressure(mainStats.get("pressure").toString());
        weather.setTemparature(mainStats.get("temp").toString());
        weather.setHumidity(mainStats.get("humidity").toString());

        return weather;

    }
}
