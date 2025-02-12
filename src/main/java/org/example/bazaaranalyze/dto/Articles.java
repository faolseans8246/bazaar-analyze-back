package org.example.bazaaranalyze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articles {

    private String title;
    private String description;
    private String url;
    private String urlToImage;
}
