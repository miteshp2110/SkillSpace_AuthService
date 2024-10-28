package com.skillspace.authservice.services;


import com.skillspace.authservice.models.Otp;
import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.repository.OtpRepository;
import com.skillspace.authservice.utils.JwtUtil;
import com.skillspace.authservice.utils.OtpUtil;
import com.skillspace.authservice.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    OtpUtil otpUtil;

    @Autowired
    RequestUtil requestUtil;

    public ResponseEntity<Response> generateEmailVerificationOtp(String token) {

        token = token.replace("Bearer ", "");
        System.out.println(jwtUtil.extractUsername(token));
        Response response = new Response();
        response.setError(false);
        System.out.println(requestUtil.sentPostRequest("/emailVerification"));
        response.setMessage("Otp Sent for Email Verification");
        return ResponseEntity.status(200).body(response);
    }

    public ResponseEntity<Response> generateForgotPasswordOtp(String token) {
        token = token.replace("Bearer ", "");
        System.out.println(jwtUtil.extractUsername(token));
        Response response = new Response();
        response.setError(false);

        System.out.println(otpUtil.generateFourDigitOtp());
        System.out.println(requestUtil.sentPostRequest("/otp"));
        response.setMessage("Otp Sent for Forgot Password");
        return ResponseEntity.status(200).body(response);
    }

}
