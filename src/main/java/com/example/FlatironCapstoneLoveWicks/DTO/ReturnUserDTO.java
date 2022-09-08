package com.example.FlatironCapstoneLoveWicks.DTO;

import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import lombok.Data;

@Data
public class ReturnUserDTO extends AppUser {
    private String name;
    private String email;
}
