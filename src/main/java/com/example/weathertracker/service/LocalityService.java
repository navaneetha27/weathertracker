package com.example.weathertracker.service;

import com.example.weathertracker.Helper.LatLongHelper;
import com.example.weathertracker.entity.Locality;
import com.example.weathertracker.repo.LocalityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

@Service
public class LocalityService {
    @Autowired
    LocalityRepo localityRepo;
    @Autowired
    LatLongHelper latLongHelper;

    public Locality findLocalityByPincode(String pincode){
        Optional<Locality> optionalLocality =  localityRepo.findByPincode(pincode);
        Map<String, String> latLongMap;
        if(optionalLocality.isPresent()){
            return optionalLocality.get();
        }
        else{
          latLongMap = latLongHelper.getLatAndLangByPinCode(pincode);
        }

        Locality locality = new Locality();
        locality.setPincode(pincode);
        locality.setLatitude(latLongMap.get("latitude"));
        locality.setLongitude(latLongMap.get("longitude"));
        return localityRepo.save(locality);
    }
}
