package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${weather.api.key}")
    private String API_KEY;
    private final RestTemplate restTemplate;

    public String getWeatherData(double lat, double lon) {
        String url = API_URL + "?lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY + "&units=metric";

        return restTemplate.getForObject(url, String.class);
    }

}
