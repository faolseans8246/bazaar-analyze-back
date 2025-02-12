package org.example.bazaaranalyze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalCurrensyData {

    private String name;
    private String logoUrl;
    private String data;
    private double values;
}
