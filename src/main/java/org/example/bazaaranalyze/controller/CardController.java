package org.example.bazaaranalyze.controller;


import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.CardDto;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
@CrossOrigin(origins = "https://bazaar-analyze-front.vercel.app")
public class CardController {

    private final CardService cardService;

    @GetMapping("/take/{uuid}")
    public ResponseEntity<ApiResponse> getCard(@PathVariable UUID uuid) {
        ApiResponse response = cardService.getCardById(uuid);

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @GetMapping("/take")
    public ResponseEntity<ApiResponse> getAllCards() {
        ApiResponse response = cardService.getAllCards();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addCard(@RequestBody CardDto cardDto) {
        ApiResponse response = cardService.saveCards(cardDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PutMapping("/change/{cardId}")
    public ResponseEntity<ApiResponse> updateCard(
            @PathVariable UUID cardId,
            @RequestParam double among
    ) {
        ApiResponse response = cardService.updateCardBalance(cardId, among);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @DeleteMapping("/delete/{cardId}")
    public ResponseEntity<ApiResponse> deleteCards(@PathVariable UUID cardId) {
        ApiResponse response = cardService.deleteCard(cardId);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
