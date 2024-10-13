package com.skillspace.authservice.services;


import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.models.Users;

import com.skillspace.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    UserRepository signupRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private SpringDataWebAutoConfiguration springDataWebAutoConfiguration;


    public ResponseEntity<ResponseWIthJWT> signupUser(Users user) {

        ResponseWIthJWT response = new ResponseWIthJWT();

        try{


            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println(user.getPassword());
            signupRepository.save(user);
            response.setError(false);
            response.setMessage("Signup successful");
            response.setJwt("JWT");
            return ResponseEntity.status(201).body(response);

        }
        catch(Exception e){
            response.setError(true);
            response.setMessage("Some Error Occurred");
            response.setJwt(null);
            return ResponseEntity.status(500).body(response);
        }

    }
}
