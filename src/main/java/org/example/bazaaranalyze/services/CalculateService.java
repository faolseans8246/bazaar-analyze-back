package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalculateService {

    public ApiResponse getCalculate(String fromCurrency, String toCurrency, double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String EXTERNAL_API_URL = "https://api.exchangerate-api.com/v4/latest/";
        String url = String.format("%s%s", EXTERNAL_API_URL, fromCurrency);

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");
                if (rates.containsKey(toCurrency)) {
                    double rate = Double.parseDouble(rates.get(toCurrency).toString());
                    double convertedAmount = amount * rate;

                    return new ApiResponse("Hisoblash muvaffaqiyatli bajarildi!", true,
                            Map.of("convertedAmount", convertedAmount, "rate", rate));
                } else {
                    return new ApiResponse("Valyuta kursi topilmadi!", false, null);
                }
            } else {
                return new ApiResponse("API javobi noto‘g‘ri!", false, null);
            }
        } catch (Exception e) {
            return new ApiResponse("Server xatosi: " + e.getMessage(), false, null);
        }
    }
}
