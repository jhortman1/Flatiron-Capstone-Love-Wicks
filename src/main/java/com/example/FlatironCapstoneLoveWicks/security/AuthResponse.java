package com.example.FlatironCapstoneLoveWicks.security;

import com.example.FlatironCapstoneLoveWicks.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String email;
    private String access_token;
    private List<Role> role;
}
