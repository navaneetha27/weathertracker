package com.example.weathertracker.Helper;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class RequestHelper {

     JSONObject getResponse(String url, String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = token != null ? HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().headers("X-Authorization-Token" , token)
                .build() :
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        client.close();
         return new JSONObject(response.body());

    }
}
