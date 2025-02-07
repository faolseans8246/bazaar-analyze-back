package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.services.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{location}")
    public String getWeather(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getWeatherData(lat, lon);
    }
}
