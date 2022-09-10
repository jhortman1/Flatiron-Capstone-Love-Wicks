package com.example.FlatironCapstoneLoveWicks.service;

import com.example.FlatironCapstoneLoveWicks.DTO.AdminReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.CreateUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import com.example.FlatironCapstoneLoveWicks.model.Role;
import com.example.FlatironCapstoneLoveWicks.repository.RoleRepository;
import com.example.FlatironCapstoneLoveWicks.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ReturnUserDTO saveUser(CreateUserDTO userDTO) {
        String encodedPW = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPW);
        AppUser newUser = modelMapper.map(userDTO,AppUser.class);
        log.info("Saving User {} to Database",userDTO.getName());
        return modelMapper.map(userRepository.save(newUser), ReturnUserDTO.class);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role {} to Database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Saving Role {} to User {} Database",roleName,email);
        Optional<AppUser> user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.get().getRoles().add(role);
    }

    @Override
    public ReturnUserDTO getUser(String email) {
        log.info("Getting User {} from Database",email);
        return modelMapper.map(userRepository.findByEmail(email).get(),ReturnUserDTO.class);
    }

    @Override
    public List<AdminReturnUserDTO> getUsers() {
        log.info("Getting All Users");
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, AdminReturnUserDTO.class)).toList();
    }
}
