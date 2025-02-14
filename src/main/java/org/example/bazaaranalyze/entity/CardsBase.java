package org.example.bazaaranalyze.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.bazaaranalyze.indexes.Ids;
import org.example.bazaaranalyze.roles.CardTypes;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "credit_cards")
public class CardsBase extends Ids {

    private String cardNumber;
    private String cardHolder;
    private double balance;

    @Enumerated(EnumType.STRING)
    private CardTypes cardType;
}
