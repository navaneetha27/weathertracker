package com.example.weathertracker.dto;

import lombok.Data;

@Data
public class WeatherDTO {
    String temparature;
    String lowTemparature;
    String highTemparature;
    String pressure;
    String humidity;
    String windSpeed;

    public String getTemparature() {
        return temparature;
    }

    public void setTemparature(String temparature) {
        this.temparature = temparature;
    }

    public String getLowTemparature() {
        return lowTemparature;
    }

    public void setLowTemparature(String lowTemparature) {
        this.lowTemparature = lowTemparature;
    }

    public String getHighTemparature() {
        return highTemparature;
    }

    public void setHighTemparature(String highTemparature) {
        this.highTemparature = highTemparature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

}
