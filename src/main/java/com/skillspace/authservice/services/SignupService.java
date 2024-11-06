package com.skillspace.authservice.services;


import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.models.Users;

import com.skillspace.authservice.repository.UserRepository;

import com.skillspace.authservice.utils.JwtUtil;
import io.jsonwebtoken.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;


    public ResponseEntity<ResponseWIthJWT> signupStudentService(Users user) {

        ResponseWIthJWT response = new ResponseWIthJWT();

        try{

            if(checkIfExistingUser(user)) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole("student");
                userRepository.save(user);
                response.setError(false);
                response.setMessage("Signup successful");
                response.setJwt(jwtUtil.generateToken(user.getEmail(),"student"));
                response.setRole("student");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);

            }
            response.setError(true);
            response.setMessage("Id or Email already exists");
            response.setJwt(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);



        }
        catch(Exception e){
            System.out.println(e);
            response.setError(true);
            response.setMessage("Some Error Occurred");
            response.setJwt(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }


    private boolean checkIfExistingUser(Users user) {
        return userRepository.existingUser(user.getEmail()) == 0;
    }

}
