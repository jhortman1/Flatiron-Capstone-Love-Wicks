package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.AdminReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.CreateUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.UpdateUserDTO;
import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import com.example.FlatironCapstoneLoveWicks.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    AppUser saveUser(CreateUserDTO user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    AppUser getUser(String email);
    List<AdminReturnUserDTO> getUsers();
    ReturnUserDTO updateUserById(Long userId, UpdateUserDTO createUserDTO);
    ReturnUserDTO deleteUserById(Long userId);
}
