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


    public ResponseEntity<ResponseWIthJWT> signupTeacherService(Users user,String token) {

        token = token.replace("Bearer ", "");
        ResponseWIthJWT response = new ResponseWIthJWT();


        try{

            if(checkIfExistingUser(user) && isAdmin(jwtUtil.extractUsername(token))) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                response.setError(false);
                response.setMessage("Signup successful");
                response.setJwt(jwtUtil.generateToken(user.getEmail(),"teacher"));
                response.setRole("teacher");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);

            }
            response.setError(true);
            response.setMessage("Id or Email already exists or Not Authorized");
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

    public ResponseEntity<ResponseWIthJWT> signupAdminService(Users user,String token) {

        token = token.replace("Bearer ", "");
        ResponseWIthJWT response = new ResponseWIthJWT();


        try{

            if(checkIfExistingUser(user) && isAdmin(jwtUtil.extractUsername(token))) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                response.setError(false);
                response.setMessage("Signup successful");
                response.setJwt(jwtUtil.generateToken(user.getEmail(),"admin"));
                response.setRole("admin");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);

            }
            response.setError(true);
            response.setMessage("Id or Email already exists or Not Authorized");
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

    private boolean isAdmin(String email) {
        String result = userRepository.findRoleByEmail(email);
        if(result == null){
            return false;
        }
        return result.equals("admin");
    }
}
