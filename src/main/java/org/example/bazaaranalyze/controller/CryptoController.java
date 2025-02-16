package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.CryptoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crypt")
@CrossOrigin(origins = "https://bazaar-analyze-front.vercel.app/", allowedHeaders = "*")
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping("/btc")
    public ResponseEntity<ApiResponse> getBtcCrypto() {
        ApiResponse response = cryptoService.getCryptoData("BTC");

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @GetMapping("/eth")
    public ResponseEntity<ApiResponse> getEthCrypto() {
        ApiResponse response = cryptoService.getCryptoData("ETH");
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
