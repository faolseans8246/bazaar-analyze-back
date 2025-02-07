package org.example.bazaaranalyze.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.repository.WorldPopulationRepos;
import org.example.bazaaranalyze.entity.WorldPopulation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

@Service
@RequiredArgsConstructor
public class WorldPopulationService {

    private final WorldPopulationRepos worldPopulationRepos;
    private final RestTemplate restTemplate;

//    private static final String POPULATION_API_URL = "https://world-population1.p.rapidapi.com/population?country_name=World";
//    private static final String POPULATION_API_KEY = "81a792cd66mshcc882743df9f3c9p155a1ejsn45a2d38dde05";  // Replace with your actual API key

    private static final String POPULATION_API_URL = "https://world-population.p.rapidapi.com/allcountriesname";

    @Value("${population.api.key}")
    private String POPULATION_API_KEY;

//    AsyncHttpClient client = new DefaultAsyncHttpClient();
//    client.prepare("GET", "https://world-population.p.rapidapi.com/allcountriesname")
//            .setHeader("x-rapidapi-key", "81a792cd66mshcc882743df9f3c9p155a1ejsn45a2d38dde05")
//        .setHeader("x-rapidapi-host", "world-population.p.rapidapi.com")
//        .execute()
//        .toCompletableFuture()
//        .thenAccept(System.out::println)
//        .join();
//
//    client.close();

    public ApiResponse getWorldPopulation() {
        try {
            // Set headers for the API request
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", POPULATION_API_KEY);
            headers.set("X-RapidAPI-Host", "world-population1.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Send GET request to API and capture the response body
            ResponseEntity<String> responseEntity = restTemplate.exchange(POPULATION_API_URL, HttpMethod.GET, entity, String.class);
            String response = responseEntity.getBody();

            // Parse the JSON response using Jackson
            JsonNode rootNode = new ObjectMapper().readTree(response); // Use ObjectMapper to parse JSON
            JsonNode bodyNode = rootNode.path("body");

            // Extract values from the response
            long totalPopulation = bodyNode.path("population").asLong();
            long malePopulation = bodyNode.path("male").asLong();
            long femalePopulation = bodyNode.path("female").asLong();
            long childrenPopulation = bodyNode.path("children").asLong();

            // Create a WorldPopulation entity and populate it with data
            WorldPopulation worldPopulation = new WorldPopulation();
            worldPopulation.setTotal(totalPopulation);
            worldPopulation.setMale(malePopulation);
            worldPopulation.setFemale(femalePopulation);
            worldPopulation.setChildren(childrenPopulation);

            // Optionally, save the data into the repository if you need to persist it
            worldPopulationRepos.save(worldPopulation);

            // Return the response with success message
            return new ApiResponse("World population data retrieved successfully.", true, worldPopulation);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("Failed to retrieve world population data.", false, null);
        }
    }
}
