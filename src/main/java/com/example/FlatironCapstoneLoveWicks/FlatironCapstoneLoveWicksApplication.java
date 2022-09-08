package com.example.FlatironCapstoneLoveWicks;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateUserDTO;
import com.example.FlatironCapstoneLoveWicks.model.Role;
import com.example.FlatironCapstoneLoveWicks.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FlatironCapstoneLoveWicksApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlatironCapstoneLoveWicksApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser(new CreateUserDTO("James Hortman","123 Main St","JamesH@email.com","4045551234",passwordEncoder.encode("pw1234")));
			userService.saveUser(new CreateUserDTO("John Smith","345 Main St","JohnS@email.com","4841231555",passwordEncoder.encode("pw1234")));
			userService.saveUser(new CreateUserDTO("Mary Lee","456 Main St","MaryL@email.com","4145559999",passwordEncoder.encode("pw1234")));
			userService.saveUser(new CreateUserDTO("Rick Perry","567 Main St","RickP@email.com","4145554568",passwordEncoder.encode("pw1234")));

			userService.addRoleToUser("JamesH@email.com","ROLE_ADMIN");
			userService.addRoleToUser("JamesH@email.com","ROLE_USER");
			userService.addRoleToUser("JohnS@email.com","ROLE_USER");
			userService.addRoleToUser("MaryL@email.com","ROLE_USER");
			userService.addRoleToUser("RickP@email.com","ROLE_USER");
		};
	}

}
