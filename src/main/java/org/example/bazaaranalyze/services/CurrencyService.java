package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.CurrencyData;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private static final String CBU_API_URL = "https://cbu.uz/uz/arkhiv-kursov-valyut/json/";
    private final RestTemplate restTemplate;

    public ApiResponse getCurrencyNotes() {
        try {
            List<Map<String, Object>> response = restTemplate.getForObject(CBU_API_URL, List.class);

            if (response == null || response.isEmpty()) {
                return new ApiResponse("Data not found!", false);
            }

            // Kutilayotgan valyutalar
            List<String> requiredCurrencies = Arrays.asList("USD", "EUR", "RUB", "CNY", "AFN", "INR", "KZT");
            List<CurrencyData> currencyDataList = new ArrayList<>();

            for (Map<String, Object> map : response) {
                String currencyCode = (String) map.get("Ccy");

                if (requiredCurrencies.contains(currencyCode)) {
                    CurrencyData currency = new CurrencyData();
                    currency.setCcy(currencyCode);
                    currency.setCcyNm_UZ((String) map.get("CcyNm_UZ"));

                    // `Rate` qiymatini String sifatida olish va `Double`ga o‘girish
                    String rateString = (String) map.get("Rate");
                    Double rate = Double.parseDouble(rateString);

                    currency.setRate(rate);
                    currencyDataList.add(currency);
                }
            }

            return new ApiResponse("Data retrieved successfully", true, currencyDataList);
        } catch (Exception e) {
            return new ApiResponse("Error fetching currency data", false);
        }
    }
}
