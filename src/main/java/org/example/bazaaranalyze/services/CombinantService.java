package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CombinantService {

    private static final String FRED_API_URL = "https://api.stlouisfed.org/fred/series/observations";

    @Value("${combinant.api.key}")
    private String fredApiKey;

    public ApiResponse getCombinant() {
        RestTemplate restTemplate = new RestTemplate();

        // FRED API orqali oltin, kumush, neft, gaz narxlarini olish
        Map<String, Object> metalPrices = new HashMap<>();
        metalPrices.put("Gold", fetchLatestPrice("GOLDPMGBD228NLBM", restTemplate));
        metalPrices.put("Silver", fetchLatestPrice("SLVPRUSD", restTemplate));
        metalPrices.put("Oil", fetchLatestPrice("DCOILWTICO", restTemplate));
        metalPrices.put("Gas", fetchLatestPrice("PNGASUSDM", restTemplate));

        return new ApiResponse("Metal prices retrieved successfully", true, metalPrices);
    }

    private String fetchLatestPrice(String seriesId, RestTemplate restTemplate) {
        String url = FRED_API_URL + "?series_id=" + seriesId + "&api_key=" + fredApiKey + "&file_type=json";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> observations = (List<Map<String, Object>>) response.get("observations");

            if (observations != null && !observations.isEmpty()) {
                return observations.get(0).get("value").toString(); // Faqat "value" qiymatini qaytaramiz
            }
            return "Data not found";
        } catch (Exception e) {
            return "Data not found";
        }
    }
}
