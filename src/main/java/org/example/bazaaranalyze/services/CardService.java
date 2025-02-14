package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.CardDto;
import org.example.bazaaranalyze.entity.CardsBase;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.repository.CardRepos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepos cardRepos;

    // Get all cards
    public ApiResponse getAllCards() {
        List<CardsBase> allCards = cardRepos.findAll();

        if (allCards.isEmpty())
            return new ApiResponse("Cards not found", false);

        return new ApiResponse("Cards found", true, allCards);
    }

    // Get card by ID
    public ApiResponse getCardById(UUID cardId) {
        Optional<CardsBase> getCardId = cardRepos.findById(cardId);

        return getCardId.map(
                cardsBase -> new ApiResponse("Card found", true, cardsBase))
                .orElseGet(() -> new ApiResponse("Card not found", false));
    }

    public ApiResponse saveCards(CardDto  cardDto) {
        CardsBase cardsBase = new CardsBase();

        if (cardDto.getCardNumber().length() == 16 && cardDto.getCardNumber().matches("[0-9]{16}"))
            cardsBase.setCardNumber(cardDto.getCardNumber());
        else
            return new ApiResponse("Invalid card number", false);

        cardsBase.setCardHolder(cardDto.getCardHolder());
        cardsBase.setBalance(cardDto.getBalance());

        cardRepos.save(cardsBase);
        return new ApiResponse("Cards saved", true, cardsBase);
    }

    public ApiResponse deleteCard(UUID cardId) {
        cardRepos.deleteById(cardId);
        return new ApiResponse("Card deleted", true, cardRepos.findById(cardId));
    }

    public ApiResponse updateCardBalance(UUID cardId, double balance) {
        CardsBase getCardId = cardRepos.findById(cardId).orElseThrow();
        getCardId.setBalance(getCardId.getBalance() + balance);

        return new ApiResponse("Card added new balance!", true, getCardId);
    }
}
