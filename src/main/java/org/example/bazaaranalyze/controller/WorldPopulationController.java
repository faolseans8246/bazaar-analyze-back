package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.WorldPopulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/population")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://bazaar-analyze-front.vercel.app")
public class WorldPopulationController {

    private final WorldPopulationService worldPopulationService;

    @GetMapping("/world")
    public ResponseEntity<ApiResponse> getWorldPopulation() {
        ApiResponse response = worldPopulationService.getWorldPopulation();

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
