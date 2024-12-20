package com.skillspace.authservice.controllers;

import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.models.Users;
import com.skillspace.authservice.services.LoginService;
import com.skillspace.authservice.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private SignupService signupService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/signupStudent")
    public ResponseEntity<ResponseWIthJWT> signupStudentController(@RequestBody Users user)
    {

        return signupService.signupStudentService(user);

    }


    @PostMapping("/login")
    public ResponseEntity<ResponseWIthJWT> login(@RequestBody Users user){

        return loginService.login(user);
    }


}
