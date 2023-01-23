package com.example.TokenAuth.controller;

import com.example.TokenAuth.model.JwtRequest;
import com.example.TokenAuth.services.CustomUserService;
import com.example.TokenAuth.utility.JwtUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Auth {

    @Autowired
    private JwtUtility jwtUtility;
    
    //private static final Logger log = (Logger) LoggerFactory.getLogger(Auth.class);

    @Autowired
    private CustomUserService customUserService;


    @Autowired
    private AuthenticationManager authenticationManager;

    public Auth(CustomUserService customUserService, AuthenticationManager authenticationManager){
        this.customUserService = customUserService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public String token(@RequestBody JwtRequest userLogin){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        return jwtUtility.generateToken(userLogin.getUsername());
        //return "";
    }
}
