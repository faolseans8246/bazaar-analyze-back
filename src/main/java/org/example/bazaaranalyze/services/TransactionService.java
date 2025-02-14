package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.entity.CardsBase;
import org.example.bazaaranalyze.model.TransactionModel;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.repository.CardRepos;
import org.example.bazaaranalyze.repository.TransactionRepos;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepos transactionRepos;
    private final CardRepos  cardRepos;

    public ApiResponse transfer(UUID fromCard, UUID toCard, double amount) {

        CardsBase fromCardNumber = cardRepos.findById(fromCard).orElseThrow();
        CardsBase toCardNumber = cardRepos.findById(toCard).orElseThrow();

        if (fromCardNumber.getBalance() >= amount) {
            fromCardNumber.setBalance(fromCardNumber.getBalance() - amount);
            toCardNumber.setBalance(toCardNumber.getBalance() + amount);

            cardRepos.save(fromCardNumber);
            cardRepos.save(toCardNumber);

            TransactionModel  transactionModel = new TransactionModel();
            transactionModel.setFromCard(fromCard.toString());
            transactionModel.setToCard(toCard.toString());
            transactionModel.setAmount(amount);
            transactionRepos.save(transactionModel);

            return new ApiResponse("Transaction done successfully", true, transactionModel);
        }

        return new ApiResponse("did not transaction", false, null);
    }
}
