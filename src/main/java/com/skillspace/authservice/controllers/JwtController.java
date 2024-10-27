package com.skillspace.authservice.controllers;


import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.services.JWTStatusService;
import com.skillspace.authservice.services.RefreshTokenService;
import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JWTStatusService jwtStatusService;

    @PostMapping("/jwtStatus")
    public ResponseEntity<Response> jwtStatus(@RequestHeader("Authorization") String token) {
        return jwtStatusService.getJwtStatus(token);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ResponseWIthJWT> refreshToken(@RequestHeader("Authorization") String token) {

        return refreshTokenService.getRefreshToken(token);
    }
}
