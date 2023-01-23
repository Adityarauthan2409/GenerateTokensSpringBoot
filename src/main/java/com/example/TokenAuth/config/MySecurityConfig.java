package com.example.TokenAuth.config;

import com.example.TokenAuth.services.CustomUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    private CustomUserService customUserService;

    //private AbstractConfiguredSecurityBuilder<AuthenticationManager, AuthenticationManagerBuilder> auth;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .authorizeRequests()
            .antMatchers("/token").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.authenticationProvider(daoAuthenticationProvider());
            return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }



    // @Bean
    // public UserDetailsService users(){
    //     return new InMemoryUserDetailsManager(
    //         User.withUsername("adi")
    //         .password("{noop}password")
    //         .authorities("read")
    //         .build()
    //     );
    // }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider obj = new DaoAuthenticationProvider();
        obj.setUserDetailsService(customUserService);
        obj.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return obj;
    }


    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     //return new BCryptPasswordEncoder(10);
    // }

}