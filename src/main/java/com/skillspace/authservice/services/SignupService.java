package com.skillspace.authservice.services;


import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.models.Users;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

@Service
public class SignupService {


    public ResponseEntity<Response> signupUser(Users user) {

        Response response = new Response();
        response.setError(false);
        response.setMessage("A error occured");

        return ResponseEntity.status(401).body(response);
    }
}
