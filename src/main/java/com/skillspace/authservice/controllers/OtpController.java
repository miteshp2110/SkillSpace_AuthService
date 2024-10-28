package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Otp;
import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.models.UpdatePassword;
import com.skillspace.authservice.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/requestOtp")
    public ResponseEntity<Response> requestOtp(@RequestHeader("Authorization") String token) {

        return otpService.generateEmailVerificationOtp(token);
    }

    @PostMapping("/requestForgotPassword")
    public ResponseEntity<Response> requestForgotPassword(@RequestBody Map<String, String> body) {
        return otpService.generateForgotPasswordOtp(body.get("email"));
    }


    @PostMapping("/verifyOtp")
    public ResponseEntity<Response> verifyOtp(@RequestBody Otp  otp) {

        return otpService.verifyOtp(otp);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Response> updatePassword(@RequestBody UpdatePassword updatePassword) {

        return otpService.updatePassword(updatePassword);
    }


}
