package org.example.bazaaranalyze.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bazaaranalyze.roles.CardTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private String cardNumber;
    private String cardHolder;
    private double balance;

    @Enumerated(EnumType.STRING)
    private CardTypes  cardType;
}
