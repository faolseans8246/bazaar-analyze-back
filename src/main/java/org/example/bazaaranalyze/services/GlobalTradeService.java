package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.GlobalCurrensyData;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GlobalTradeService {

    private final RestTemplate restTemplate;

    @Value("${api.exchangerates.key}")
    private String exchangeRateApiKey;

    public ApiResponse getGlobalExchangeRates() {
        String url = "https://v6.exchangerate-api.com/v6/" + exchangeRateApiKey + "/latest/USD";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response == null || !response.containsKey("conversion_rates")) {
                return new ApiResponse("Failed to fetch exchange rates", false);
            }

            Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");
            List<GlobalCurrensyData> tradeDataResult = new ArrayList<>();

            List<String> currencies = Arrays.asList("EUR", "GBP", "JPY", "CNY", "RUB", "UZS", "BTC");

            for (String currency : currencies) {
                if (rates.containsKey(currency)) {
                    tradeDataResult.add(new GlobalCurrensyData(
                            currency,
                            getCurrencyLogoUrl(currency),
                            LocalDate.now().toString(),
                            rates.get(currency)
                    ));
                }
            }

            return new ApiResponse("Data successfully loaded", true, tradeDataResult);
        } catch (Exception e) {
            return new ApiResponse("Error fetching data: " + e.getMessage(), false);
        }
    }

    private String getCurrencyLogoUrl(String currency) {
        Map<String, String> currencyLogos = Map.of(
                "EUR", "https://www.example.com/euro-logo.png",
                "GBP", "https://www.example.com/gbp-logo.png",
                "JPY", "https://www.example.com/jpy-logo.png",
                "CNY", "https://www.example.com/cny-logo.png",
                "RUB", "https://www.example.com/rub-logo.png",
                "UZS", "https://www.example.com/uzs-logo.png"
        );
        return currencyLogos.getOrDefault(currency, "https://www.example.com/default-logo.png");
    }
}
