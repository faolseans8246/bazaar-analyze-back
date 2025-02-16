package org.example.bazaaranalyze.controller;


import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.CombinantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/combinant")
@CrossOrigin(origins = "https://bazaar-analyze-front.vercel.app")
public class CombinantController {

    private final CombinantService combiantService;

    @GetMapping("/metalls")
    public ResponseEntity<ApiResponse> getCombinant() {
        ApiResponse apiResponse = combiantService.getCombinant();

        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
