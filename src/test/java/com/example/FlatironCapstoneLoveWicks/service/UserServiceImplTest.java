package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.AdminReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.CreateUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.UpdateUserDTO;
import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import com.example.FlatironCapstoneLoveWicks.model.CandleOrder;
import com.example.FlatironCapstoneLoveWicks.model.Role;
import com.example.FlatironCapstoneLoveWicks.repository.RoleRepository;
import com.example.FlatironCapstoneLoveWicks.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void saveUser() {
        AppUser appUser = new AppUser();
        appUser.setName("James");
        appUser.setAddress("123 Main St");
        appUser.setEmail("j@email.com");
        appUser.setPhone("4445550000");
        appUser.setPassword(passwordEncoder.encode("pw1234"));

        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("James");
        createUserDTO.setAddress("123 Main St");
        createUserDTO.setEmail("j@email.com");
        createUserDTO.setEmail("4445550000");
        createUserDTO.setPassword("pw1324");

        when(userRepository.findByEmail(createUserDTO.getEmail())).thenReturn(Optional.empty());

        when(modelMapper.map(createUserDTO,AppUser.class)).thenReturn(appUser);

        ReturnUserDTO returnedUserDTO = new ReturnUserDTO();
        returnedUserDTO.setName("James");
        returnedUserDTO.setAddress("123 Main St");
        returnedUserDTO.setEmail("j@email.com");
        returnedUserDTO.setEmail("4445550000");
        returnedUserDTO.setPassword("pw1324");

        when(modelMapper.map(userRepository.save(appUser),ReturnUserDTO.class)).thenReturn(returnedUserDTO);

        ReturnUserDTO actual = userService.saveUser(createUserDTO);
        Assertions.assertEquals(returnedUserDTO,actual,"Users created successful");
    }

    @Test
    void saveRole() {
        Role returnedRole = new Role();
        returnedRole.setName("ROLE_ADMIN");
        returnedRole.setId(1L);
        when(roleRepository.save(returnedRole)).thenReturn(returnedRole);
        Role actual = userService.saveRole(returnedRole);
        assertEquals(returnedRole, actual);
    }

    @Test
    void getUsers() {
        List<AdminReturnUserDTO> returnedDtos = new ArrayList<>();
        when(userRepository.findAll().stream().filter(user->user.getIsActive()).map(user -> modelMapper.map(user, AdminReturnUserDTO.class)).toList())
                .thenReturn(returnedDtos);
        List<AdminReturnUserDTO> actual = userService.getUsers();
        assertEquals(returnedDtos,actual);
    }

    @Test
    void updateUserById() {
        Long id = 1L;
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        AppUser newUser = new AppUser();
        when(userRepository.findById(id)).thenReturn(Optional.of(newUser));
        ReturnUserDTO returnedDTO = new ReturnUserDTO();
        returnedDTO.setId(id);
        when(modelMapper.map(newUser,ReturnUserDTO.class)).thenReturn(returnedDTO);
        ReturnUserDTO actual = userService.updateUserById(id,updateUserDTO);
        assertEquals(returnedDTO,actual);
    }

    @Test
    void deleteUserById() {
        Long id = 1L;
        AppUser deleteUser = new AppUser();
        when(userRepository.findById(id)).thenReturn(Optional.of(deleteUser)).thenReturn(Optional.of(deleteUser));
        ReturnUserDTO returnUserDTO = new ReturnUserDTO();
        when(modelMapper.map(deleteUser, ReturnUserDTO.class)).thenReturn(returnUserDTO);
        ReturnUserDTO actual = userService.deleteUserById(id);
        assertEquals(returnUserDTO,actual);
    }
}