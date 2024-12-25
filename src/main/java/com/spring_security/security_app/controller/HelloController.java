package com.spring_security.security_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("check")
    public String check(HttpServletRequest request){
        return "working and seesion id is -" + request.getSession().getId();
    }
}
