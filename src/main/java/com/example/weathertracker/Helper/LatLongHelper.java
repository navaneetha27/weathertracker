package com.example.weathertracker.Helper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LatLongHelper {

    @Autowired
    RequestHelper requestHelper;
    @Value("${latlong.api.key}")
    String apiKey;
    String urlFormatter = "https://apihub.latlong.ai/v4/geocode.json?address=%s";
    public Map<String, String> getLatAndLangByPinCode(String pincode){
        Map<String ,String> latLangMap = new HashMap<>();
        String url = String.format(urlFormatter,pincode);
        try {
            JSONObject response = requestHelper.getResponse(url, apiKey);
            latLangMap.put("latitude", response.getJSONObject("data").getString("latitude"));
            latLangMap.put("longitude",response.getJSONObject("data").getString("longitude"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return latLangMap;
     }
}
