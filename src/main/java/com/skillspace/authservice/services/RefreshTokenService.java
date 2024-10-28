package com.skillspace.authservice.services;

import com.skillspace.authservice.models.ResponseWIthJWT;
import com.skillspace.authservice.models.Token;
import com.skillspace.authservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
    @Autowired
    JwtUtil jwtUtil;




    public ResponseEntity<ResponseWIthJWT> getRefreshToken(String currentToken) {
        ResponseWIthJWT response = new ResponseWIthJWT();

        currentToken = currentToken.replace("Bearer ", "");

        Token currentTokenStatus = jwtUtil.tokenStatus(currentToken);

        if(currentTokenStatus.isValid() && !currentTokenStatus.isExpired()){

            response.setJwt(currentToken);
            response.setError(false);
            response.setMessage("Refresh token still Valid.");
            response.setRole(jwtUtil.extractRole(currentToken));
            return ResponseEntity.status(200).body(response);

        }
        else{

            if(currentTokenStatus.isExpired() & currentTokenStatus.isValid()){
                String newToken = jwtUtil.getRefreshToken(currentToken);
                response.setJwt(newToken);
                response.setError(false);
                response.setMessage("Refresh token Updated.");
                response.setRole(jwtUtil.extractRole(newToken));
                return ResponseEntity.status(200).body(response);
            }

            else{
                response.setJwt(null);
                response.setError(true);
                response.setMessage("Current Token Invalid.");
                return ResponseEntity.status(401).body(response);
            }


        }

    }



}
