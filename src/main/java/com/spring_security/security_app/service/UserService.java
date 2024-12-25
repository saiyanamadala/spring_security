package com.spring_security.security_app.service;

import com.spring_security.security_app.model.Users;
import com.spring_security.security_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo urepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public Users addUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return urepo.save(user);
    }
}
