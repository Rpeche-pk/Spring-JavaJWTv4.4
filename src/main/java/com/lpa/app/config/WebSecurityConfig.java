package com.lpa.app.config;

import com.lpa.app.security.JwtFilterAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final JwtFilterAuth jwtFilterAuth;

    public WebSecurityConfig(JwtFilterAuth jwtFilterAuth) {
        this.jwtFilterAuth = jwtFilterAuth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/user").permitAll();
                    auth.requestMatchers("/sign-in").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilterAuth, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
