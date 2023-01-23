package com.example.TokenAuth;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
public class TokenAuthApplication{

	public static void main(String[] args) {
		SpringApplication.run(TokenAuthApplication.class, args);
	}

	// @Override
	// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// 	return new User("admin","password",new ArrayList<>());

	// }

}
