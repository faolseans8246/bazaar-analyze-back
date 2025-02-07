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
@RequiredArgsConstructor
@RequestMapping("/api/population")
@CrossOrigin(origins = "http://localhost:3000")
public class WorldPopulationController {

    private final WorldPopulationService worldPopulationService;


    @GetMapping("/world")
    public ResponseEntity<ApiResponse> populations() {
        ApiResponse apiResponse = worldPopulationService.getWorldPopulation();

        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
