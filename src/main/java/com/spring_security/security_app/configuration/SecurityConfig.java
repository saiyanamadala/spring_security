package com.spring_security.security_app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

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

    @Bean
    public UserDetailsService userDetailsService(){                     //UserDetailsService is used to verify from database
        UserDetails user1 = User                                        //User implements UserDetails interface
                .withDefaultPasswordEncoder()
                .username("sai")
                .password("sai@123")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("ram")
                .password("ram@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);                        //UserDetailsService is an Interface and InMemoryUserDetailsManager implements it
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();    //DaoAuthenticationProvider implements AuthenticationProvider
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());         //not using any password encoders
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
