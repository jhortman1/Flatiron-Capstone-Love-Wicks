package com.example.FlatironCapstoneLoveWicks;

import com.example.FlatironCapstoneLoveWicks.DTO.CreateCandleDTO;
import com.example.FlatironCapstoneLoveWicks.DTO.CreateUserDTO;
import com.example.FlatironCapstoneLoveWicks.model.Role;
import com.example.FlatironCapstoneLoveWicks.service.CandleService;
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

}
