package com.example.FlatironCapstoneLoveWicks.Controller;

import com.example.FlatironCapstoneLoveWicks.DTO.AdminReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.CreateUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.ReturnUserDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.UpdateUserDTO;
import com.example.FlatironCapstoneLoveWicks.model.AppUser;
import com.example.FlatironCapstoneLoveWicks.model.Role;
import com.example.FlatironCapstoneLoveWicks.service.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public ResponseEntity<List<AdminReturnUserDTO>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public ResponseEntity<ReturnUserDTO> signUpCustomer(@RequestBody CreateUserDTO user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/signup").toUriString());
        passwordEncoder.encode(user.getPassword());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/role/assignRoleToUser")
    public ResponseEntity<?> assignRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(),form.getRoleName());
        return ResponseEntity.ok().build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/user/{id}")
    public ResponseEntity<ReturnUserDTO>updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO updateUserDTO)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/{id}").toUriString());
        return ResponseEntity.created(uri).body(userService.updateUserById(id,updateUserDTO));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<ReturnUserDTO> deactivateUser(@PathVariable("id")Long id)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/{id}").toUriString());
        return ResponseEntity.created(uri).body(userService.deleteUserById(id));
    }
}
@Data
class RoleToUserForm
{
    private String email;
    private String roleName;
}
