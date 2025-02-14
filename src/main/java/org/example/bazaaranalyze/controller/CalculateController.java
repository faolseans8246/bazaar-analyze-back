package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calculate")
@CrossOrigin(origins = "http://localhost:3000")
public class CalculateController {

    private final CalculateService calculateService;

    @GetMapping("/rates")
    public ResponseEntity<ApiResponse> calculateRate(@RequestParam String from, @RequestParam String to) {
        ApiResponse apiResponse = calculateService.getCalculate(from, to);

        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
