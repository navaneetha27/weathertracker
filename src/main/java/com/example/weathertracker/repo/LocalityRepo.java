package com.example.weathertracker.repo;

import com.example.weathertracker.entity.Locality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalityRepo  extends JpaRepository<Locality, Long> {
    Optional<Locality> findByPincode(String  pincode);
}
