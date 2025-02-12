package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.Articles;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsService {

    @Value("${daily.news.key}")
    private String API_KEY;
    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";


    public ApiResponse getDailyNews() {

        try {

            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> map = restTemplate.getForObject(NEWS_URL + API_KEY, Map.class);
            List<Map<String, String>> newsList = (List<Map<String, String>>) map.get("articles");

            List<Articles> articles = newsList.stream()
                    .map(news -> new Articles(
                            news.get("title"),
                            news.get("description"),
                            news.get("url"),
                            news.get("urlToImage")))
                    .toList();

            return new ApiResponse("News get successfully", true, articles);

        } catch (Exception e) {
            return new ApiResponse("Back-end error", false);
        }
    }
}
