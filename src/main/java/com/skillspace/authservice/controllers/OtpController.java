package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Otp;
import com.skillspace.authservice.models.RequestOtp;
import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/requestOtp")
    public ResponseEntity<Response> requestOtp(@RequestHeader("Authorization") String token , @RequestBody RequestOtp requestOtp) {
//        System.out.println(token);
        if(requestOtp.isForgotPassword()) {
            System.out.println("Forgot Password");
            return otpService.generateForgotPasswordOtp(token);
        }
        else{

            System.out.println("email verification");
            return otpService.generateEmailVerificationOtp(token);
        }
    }



    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestBody Otp otp){
        System.out.println(otp.getCode());
        return "Verifying OTP";
    }
}
