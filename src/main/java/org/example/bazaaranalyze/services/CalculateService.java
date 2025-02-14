package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
@RequiredArgsConstructor
public class CalculateService {

    public ApiResponse getCalculate(String fromCurrency, String toCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String EXTERNAL_API_URL = "https://api.exchangerate-api.com/v4/latest/";
        String url = String.format("%s/%s", EXTERNAL_API_URL, fromCurrency);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                double rate = extractRateFromJson(responseBody, toCurrency);

                if (rate > 0) {
                    return new ApiResponse("Ma'lumotlar muvaffaqiyatli olindi!", true, rate);
                } else {
                    return new ApiResponse("Kurs ma'lumotlari noto'g'ri!", false, null);
                }
            } else {
                return new ApiResponse("Ma'lumotlar olinmadi!", false, null);
            }
        } catch (Exception e) {
            return new ApiResponse("Xatolik yuz berdi!", false, null);
        }
    }

    private double extractRateFromJson(String json, String toCurrency) {
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
            return jsonObject.getJSONObject("rates").getDouble(toCurrency);
        } catch (Exception e) {
            return 0;
        }
    }

}