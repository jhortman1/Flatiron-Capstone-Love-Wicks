package com.example.FlatironCapstoneLoveWicks.DTO;

import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnUserDTO extends AppUser {
    private String name;
    private String email;
}
