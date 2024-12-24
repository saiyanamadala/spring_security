package com.spring_security.security_app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {

        http.csrf(customizer->customizer.disable());  //disabling csrf for implementing STATELESS REST Api
        http.authorizeHttpRequests(request->request.anyRequest().authenticated());  //any request must be authenticated
        //http.formLogin(Customizer.withDefaults()); //enables form login with defaults in application.properties
        //formLogin() is disabled because the
        http.httpBasic(Customizer.withDefaults()); //enables authentication for postman
        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //implementing STATELESS REST Api

        return http.build();
    }
}
