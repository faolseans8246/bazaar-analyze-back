package org.example.bazaaranalyze.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.bazaaranalyze.indexes.Ids;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction")
public class TransactionModel extends Ids {

    private String fromCard;
    private String toCard;
    private double amount;

    private LocalDateTime time;
}
