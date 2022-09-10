package com.example.FlatironCapstoneLoveWicks.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminReturnUserDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private Boolean isActive;
}
