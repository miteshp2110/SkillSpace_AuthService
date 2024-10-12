package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Otp;
import com.skillspace.authservice.models.RequestOtp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    @PostMapping("/requestOtp")
    public String requestOtp(@RequestHeader("Authorization") String token , @RequestBody RequestOtp requestOtp) {
        System.out.println(token);
        if(requestOtp.isForgotPassword()) {
            System.out.println("Forgot Password");
        }
        else{
            System.out.println("email verification");
        }
        return "Sending OTP";
    }



    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestBody Otp otp){
        System.out.println(otp.getCode());
        return "Verifying OTP";
    }
}
