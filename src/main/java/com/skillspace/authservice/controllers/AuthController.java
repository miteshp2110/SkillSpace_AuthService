package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.models.Users;
import com.skillspace.authservice.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody Users user)
    {
        System.out.println(user);
        return signupService.signupUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){

        System.out.println(user);
        return "you are logging in";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestBody String newPassword){
        System.out.println(newPassword);
        return "update password";
    }

}
