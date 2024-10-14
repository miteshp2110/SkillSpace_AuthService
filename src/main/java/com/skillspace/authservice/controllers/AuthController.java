package com.skillspace.authservice.controllers;

import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.models.Users;
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

    @PostMapping("/signupStudent")
    public ResponseEntity<ResponseWIthJWT> signupStudentController(@RequestBody Users user)
    {

        return signupService.signupStudentService(user);

    }

    @PostMapping("/signupTeacher")
    public ResponseEntity<ResponseWIthJWT> signupTeacherController(@RequestBody Users user, @RequestHeader("Authorization") String token){
        return signupService.signupTeacherService(user,token);
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<ResponseWIthJWT> signupAdminController(@RequestBody Users user, @RequestHeader("Authorization") String token){
        return signupService.signupAdminService(user,token);

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
