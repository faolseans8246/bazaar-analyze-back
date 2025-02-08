package org.example.bazaaranalyze.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

@Service
@RequiredArgsConstructor
public class WorldPopulationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final String POPULATION_API_URL = "https://world-population-by-decade-and-growth-rate.p.rapidapi.com/yDRaVL/world_population_by_decade?Year=value";

    @Value("${world.population.key}")
    private String API_KEY;

    public ApiResponse getWorldPopulation() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-key", API_KEY);
            headers.set("x-rapidapi-host", "world-population-by-decade-and-growth-rate.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(POPULATION_API_URL, HttpMethod.GET, entity, String.class);

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                JsonNode root = objectMapper.readTree(response.getBody());
                Long population = root.path("data").path("population").asLong();

                return new ApiResponse("World population retrieved successfully", true, population);
            } else {
                return new ApiResponse("Failed to fetch population data", false);
            }
        } catch (Exception e) {
            return new ApiResponse("Error occurred: " + e.getMessage(), false);
        }
    }
}
