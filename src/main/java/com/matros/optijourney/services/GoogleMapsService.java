package com.matros.optijourney.services;

import com.matros.optijourney.dtos.DistanceResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {
    private final RestTemplate restTemplate;

    @Value("${googlemaps.apikey}")
    private String apiKey;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DistanceResponseDTO getDistance(String[] points, String mode) {

        String pointsStr = "";
        for (String s : points) {
            pointsStr += s + "|";
        }

        String url = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&units=metric&mode=%s&key=%s",
                pointsStr, pointsStr, mode, apiKey
        );

        return restTemplate.getForObject(url, DistanceResponseDTO.class);
    }
}
