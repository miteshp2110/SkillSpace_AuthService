package com.skillspace.authservice.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @PostMapping("/jwtStatus")
    public String jwtStatus(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        return "You will get jwt status";
    }

    @PostMapping("/refreshToken")
    public String refreshToken(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        return "You will get refresh token";
    }
}
