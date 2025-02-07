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
@Entity(name = "location_now")
public class LocationAddressBase extends Ids {

    private String ip;
    private String country;
    private String city;
    private String latitude;
    private String longitude;

}