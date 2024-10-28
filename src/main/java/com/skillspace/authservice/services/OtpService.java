package com.skillspace.authservice.services;


import com.skillspace.authservice.models.*;
import com.skillspace.authservice.repository.OtpRepository;
import com.skillspace.authservice.repository.UserRepository;
import com.skillspace.authservice.utils.JwtUtil;
import com.skillspace.authservice.utils.OtpUtil;
import com.skillspace.authservice.utils.RequestUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    OtpUtil otpUtil;

    @Autowired
    RequestUtil requestUtil;

    @Autowired
    RequestBody requestBody;


    Response response = new Response();

    final String companyName = "SkillSpace";
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Response> generateEmailVerificationOtp(String token) {

        token = token.replace("Bearer ", "");

        try{
            int code =  otpUtil.generateFourDigitOtp();
            String email = jwtUtil.extractUsername(token);
            Otp otp = new Otp();
            otp.setEmail(email);
            otp.setCode(code);
            otpRepository.save(otp);
            String reqBody = requestBody.otpRequestBody(email,companyName,code);
            requestUtil.sentPostRequest("/emailVerification",reqBody);
            response.setMessage("Mail Sent Successfully");
            return ResponseEntity.status(200).body(response);
        }
        catch (Exception e){
            response.setError(true);
            response.setMessage("Unable to send Mail, Try after some time.");
            return ResponseEntity.status(500).body(response);
        }
    }

    public ResponseEntity<Response> generateForgotPasswordOtp(String email) {


       try{
           int code =  otpUtil.generateFourDigitOtp();
           Otp otp = new Otp();
           otp.setEmail(email);
           otp.setCode(code);
           otpRepository.save(otp);
           String reqBody = requestBody.otpRequestBody(email,companyName,code);
           requestUtil.sentPostRequest("/otp",reqBody);
           response.setMessage("Mail sent Successfully");
           return ResponseEntity.status(200).body(response);
       }
       catch (Exception e){
           response.setError(true);
           response.setMessage("Unable to send Mail, Try after some time.");
           return ResponseEntity.status(500).body(response);
       }
    }

    public ResponseEntity<Response> verifyOtp( Otp otp) {

        Object code = otpRepository.getOtpCode(otp.getEmail());

        if(code == null){
            response.setError(true);
            response.setMessage("Otp code not found");
            return ResponseEntity.status(404).body(response);
        }
        else{
            if(Integer.parseInt(code.toString()) == otp.getCode()){

                userRepository.updateEmailStatus(otp.getEmail(), true);
                otpRepository.delete(otp);
                response.setError(false);
                response.setMessage("Verification Successful");
                return ResponseEntity.status(200).body(response);
            }
            else{
                response.setError(true);
                response.setMessage("Wrong OTP");
                return ResponseEntity.status(404).body(response);
            }
        }

    }


    public ResponseEntity<Response> updatePassword(UpdatePassword updatePassword) {

        Object code = otpRepository.getOtpCode(updatePassword.getEmail());

        if(code == null){
            response.setError(true);
            response.setMessage("Otp code not found");
            return ResponseEntity.status(404).body(response);
        }
        else{
            if(Integer.parseInt(code.toString()) == updatePassword.getOtp()){

                userRepository.updatePassword(passwordEncoder.encode(updatePassword.getNewPassword()),updatePassword.getEmail());
                Otp otp1 = new Otp();
                otp1.setEmail(updatePassword.getEmail());
                otp1.setCode(updatePassword.getOtp());
                otpRepository.delete(otp1);
                response.setError(false);
                response.setMessage("Update Successful");
                return ResponseEntity.status(200).body(response);
            }
            else{
                response.setError(true);
                response.setMessage("Wrong OTP");
                return ResponseEntity.status(404).body(response);
            }
        }

    }



}
