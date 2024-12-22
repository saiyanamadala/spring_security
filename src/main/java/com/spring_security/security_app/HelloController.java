package com.spring_security.security_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("check")
    public String check(){
        return "working";
    }
}
