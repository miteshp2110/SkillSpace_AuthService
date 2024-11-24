package com.skillspace.authservice.services;


import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.models.Users;
import com.skillspace.authservice.repository.UserRepository;
import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder ;

    public ResponseEntity<ResponseWIthJWT> login(Users user) {
        ResponseWIthJWT response = new ResponseWIthJWT();

        if(userRepository.existingUser(user.getEmail()) == 1){

            if(passwordEncoder.matches(user.getPassword(),userRepository.findPasswordByEmail(user.getEmail()))){


                String role = userRepository.findRoleByEmail(user.getEmail());
                response.setRole(role);
                response.setMessage("You have successfully logged in!");
                response.setError(false);
                response.setJwt(jwtUtil.generateToken(user.getEmail(),role));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            else{
                response.setJwt(null);
                response.setMessage("Wrong Password");
                response.setError(true);
                response.setRole(null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

        }
        else{
            response.setError(true);
            response.setMessage("User not found");
            response.setRole(null);
            response.setJwt(null);
            return ResponseEntity.status(404).body(response);
        }

    }
}
