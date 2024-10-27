package com.skillspace.authservice.services;

import com.skillspace.authservice.models.Response;
import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class JWTStatusService {
    @Autowired
    JwtUtil jwtUtil;
    public ResponseEntity<Response> getJwtStatus(String token) {

        token = token.replace("Bearer ", "");

        Response response = new Response();
        if (jwtUtil.tokenStatus(token).isValid()) {
            if (jwtUtil.tokenStatus(token).isExpired()) {
                response.setError(true);
                response.setMessage("Token expired");
                return ResponseEntity.status(403).body(response);
            } else {
                response.setError(false);
                response.setMessage("Token valid");
                return ResponseEntity.status(200).body(response);
            }
        }
        response.setError(true);
        response.setMessage("Token Invalid");
        return ResponseEntity.status(403).body(response);
    }
}
