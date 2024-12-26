package com.spring_security.security_app.controller;

import com.spring_security.security_app.model.Users;
import com.spring_security.security_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userv;

    @PostMapping("register")
    public Users addUser(@RequestBody Users user){
        return userv.addUser(user);
    }

    @PostMapping("login")
    public String userLogin(@RequestBody Users user){
        return userv.verify(user);
    }
}
