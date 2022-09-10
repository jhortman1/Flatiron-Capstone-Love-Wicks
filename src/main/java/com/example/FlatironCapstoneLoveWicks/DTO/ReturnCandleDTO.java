package com.example.FlatironCapstoneLoveWicks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnCandleDTO {
    private Long id;
    private String name;
    private String description;
    private int photoId;
    private double price;
    private boolean inStock;
}
