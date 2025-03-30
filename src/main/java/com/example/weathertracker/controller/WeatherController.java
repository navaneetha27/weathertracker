package com.example.weathertracker.controller;

import com.example.weathertracker.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    WeatherService weatherService;
    @GetMapping("/weather")
    ResponseEntity<Object> getWeather(@RequestParam("pincode") String pincode , @RequestParam("date") String date){

        try{
            return new ResponseEntity<>(weatherService.getWeatherByDateAndPinCode(date, pincode), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
