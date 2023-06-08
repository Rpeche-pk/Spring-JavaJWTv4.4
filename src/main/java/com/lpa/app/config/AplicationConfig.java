package com.lpa.app.config;

import com.lpa.app.security.JwtFilterAuth;
import com.lpa.app.security.JwtUtilsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AplicationConfig {

//    private final JwtUtilsProvider jwtUtilsProvider;
//
//    public AplicationConfig(JwtUtilsProvider jwtUtilsProvider) {
//        this.jwtUtilsProvider = jwtUtilsProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtFilterAuth jwtFilterAuth() {
//        return new JwtFilterAuth(jwtUtilsProvider);
//    }
}
