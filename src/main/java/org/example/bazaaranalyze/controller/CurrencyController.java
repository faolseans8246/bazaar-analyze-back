package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.CurrencyService;
import org.example.bazaaranalyze.services.GlobalTradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currency")
@CrossOrigin(origins = "https://bazaar-analyze-front.vercel.app")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final GlobalTradeService globalTradeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getCurrency() {
        ApiResponse responce = currencyService.getCurrencyNotes();
        return ResponseEntity.status(responce.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(responce);
    }


    @GetMapping("/global")
    public ResponseEntity<ApiResponse> getGlobalCurrency() {
        ApiResponse response = globalTradeService.getGlobalExchangeRates();

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
