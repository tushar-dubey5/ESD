package org.tushardubey.java.khanakhajana.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;  // Ensure this is Double, not float
}
