package org.example.bazaaranalyze.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
@CrossOrigin(origins = "https://bazaar-analyze-front.vercel.app")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/address")
    public ResponseEntity<ApiResponse> getLocations(HttpServletRequest request) {
        ApiResponse apiResponse = locationService.getLocation(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
