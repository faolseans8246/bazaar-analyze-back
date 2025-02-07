package org.example.bazaaranalyze.services;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ApiResponse getLocation(HttpServletRequest request) {
        String clientIp = getClientIp(request);
        String url = "https://ipwho.is/" + clientIp;

        try {
            Map response = restTemplate.getForObject(url, Map.class);

            if (response != null && (boolean) response.get("success")) {
                return new ApiResponse("Location found", true, response);
            } else {
                return new ApiResponse("Could not fetch location data", false);
            }
        } catch (Exception e) {
            return new ApiResponse("Error fetching location", false);
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
