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
    String urlFormat = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=$s&appid=%s";
    @Value("${weather.api.key}")
    private String apiKey;
    @Autowired RequestHelper requestHelper;

    public Weather getWeatherInfoFromOpenWeather(String latitude, String longitude) throws IOException, InterruptedException {
        String url = String.format(urlFormat, latitude, longitude, apiKey);
        JSONObject responseObject = requestHelper.getResponse(url,apiKey);
        JSONObject mainStats = responseObject.getJSONObject("main");
        String windSpeed = responseObject.getJSONObject("wind").getString("speed");
        Weather weather = new Weather();
        weather.setWindSpeed(windSpeed);
        weather.setHighTemparature(mainStats.getString("temp_max"));
        weather.setLowTemparature(mainStats.getString("temp_min"));
        weather.setPressure(mainStats.getString("pressure"));
        weather.setHumidity(mainStats.getString("humidity"));

        return weather;

    }
}
