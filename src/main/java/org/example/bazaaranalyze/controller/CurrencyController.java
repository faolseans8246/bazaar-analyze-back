package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currency")
@CrossOrigin(origins = "http://localhost:3000")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getCurrency() {
        ApiResponse responce = currencyService.getCurrencyNotes();
        return ResponseEntity.status(responce.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(responce);
    }
}
