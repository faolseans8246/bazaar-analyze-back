package org.example.bazaaranalyze.controller;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.LoginDto;
import org.example.bazaaranalyze.dto.SignupDto;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService signupService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupDto signupDto) {
        ApiResponse apiResponse = signupService.signup(signupDto);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(LoginDto loginDto) {
        ApiResponse apiResponse = signupService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
