package org.example.bazaaranalyze.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.bazaaranalyze.indexes.Ids;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorldPopulation extends Ids {

    private long total;
    private long male;
    private long female;
    private long children;
}
