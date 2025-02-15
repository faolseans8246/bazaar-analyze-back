package org.example.bazaaranalyze.services;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private static final String CRYPTO_API_URL = "https://rest.coinapi.io/v1/exchangerate/";

    @Value("${crypto.api.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    public ApiResponse getCryptoData(String assetId) {
        try {
            String url = CRYPTO_API_URL + assetId.toUpperCase();

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-CoinAPI-Key", API_KEY);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    String.class
            );

            String response = responseEntity.getBody();
            if (response != null) {
                JsonNode rootNode = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response);
                if (rootNode.isArray() && rootNode.size() > 0) {
                    JsonNode cryptoData = rootNode.get(0);
                    double price = cryptoData.get("price_usd").asDouble();

                    Map<String, Double> result = new HashMap<>();
                    result.put(assetId.toUpperCase(), price);

                    return new ApiResponse(assetId.toUpperCase() + " price fetched successfully", true, result);
                }
            }
            return new ApiResponse(assetId.toUpperCase() + " price not found", false, null);
        } catch (Exception e) {
            return new ApiResponse("Error fetching " + assetId.toUpperCase() + " data: " + e.getMessage(), false, null);
        }
    }
}
