package com.example.FlatironCapstoneLoveWicks.DTO;

import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    private AppUser appUser;
    private Boolean open;
}
