package com.example.weathertracker.repo;

import com.example.weathertracker.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeatherRepo extends JpaRepository<Weather,Long> {

    @Query(value = "SELECT * FROM weather WHERE pincode = :pinCode AND date = :date", nativeQuery = true)
    Optional<Weather> findByPinCodeAndDate(@Param("pinCode") String pinCode, @Param("date") String date);
}
