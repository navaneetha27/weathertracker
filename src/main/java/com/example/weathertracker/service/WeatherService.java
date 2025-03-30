package com.example.weathertracker.service;

import com.example.weathertracker.Exception.InvalidPinCodeException;
import com.example.weathertracker.Helper.InputValidator;
import com.example.weathertracker.Helper.LatLongHelper;
import com.example.weathertracker.Helper.WeatherHelper;
import com.example.weathertracker.dto.WeatherDTO;
import com.example.weathertracker.entity.Locality;
import com.example.weathertracker.entity.Weather;
import com.example.weathertracker.repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    WeatherRepo weatherRepo;
    @Autowired
    WeatherHelper weatherHelper;



    @Autowired
    LocalityService localityService;
    public WeatherDTO getWeatherByDateAndPinCode(String date, String pincode) throws InvalidPinCodeException, IOException, InterruptedException {
        WeatherDTO weatherDTO = new WeatherDTO();
        Weather weather1 = null;
        boolean isValidPin = InputValidator.isValidPinCode(pincode);
        if(!isValidPin){
            throw new InvalidPinCodeException();
        }
        Optional<Weather> weatherOptional = weatherRepo.findByPinCodeAndDate(pincode, date);
        if(weatherOptional.isPresent()){
            weather1 = weatherOptional.get();
        }
        else{
            Locality locality = localityService.findLocalityByPincode(pincode);
            Weather weather = weatherHelper.getWeatherInfoFromOpenWeather(locality.getLatitude(), locality.getLongitude());
            weather.setDate(date);
            weather.setPincode(pincode);
            weather1 = weatherRepo.save(weather);
        }
        transformtoDtoObject(weather1, weatherDTO);
        return weatherDTO;
    }

    private void transformtoDtoObject(Weather weather1, WeatherDTO weatherDTO) {
        weatherDTO.setHumidity(weather1.getHumidity());
        weatherDTO.setHighTemparature(weather1.getHighTemparature());
        weatherDTO.setLowTemparature(weatherDTO.getLowTemparature());
        weatherDTO.setTemparature(weather1.getTemparature());
        weatherDTO.setWindSpeed(weather1.getWindSpeed());
        weatherDTO.setPressure(weatherDTO.getPressure());
    }
}
