package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/signup")
    public String signup(@RequestBody Users user)
    {
        System.out.println(user);
        return "you are signing up";

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){

        System.out.println(user);
        return "you are logging in";
    }

}
