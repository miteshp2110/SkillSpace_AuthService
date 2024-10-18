package com.skillspace.authservice.controllers;


import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/jwtStatus")
    public String jwtStatus(@RequestHeader("Authorization") String token) {

        System.out.println(token);
        System.out.println(jwtUtil.extractRole(token));
        System.out.println(jwtUtil.isTokenExpired(token));
        return jwtUtil.extractUsername(token);
    }

    @PostMapping("/refreshToken")
    public String refreshToken(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        return "You will get refresh token";
    }
}
