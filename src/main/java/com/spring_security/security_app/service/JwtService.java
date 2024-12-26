package com.spring_security.security_app.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken() {
        return "Success";                   // write logic to provide JWT token
    }
}
