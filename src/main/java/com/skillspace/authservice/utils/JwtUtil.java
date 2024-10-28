package com.skillspace.authservice.utils;

import com.skillspace.authservice.models.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtil {

    private final String secret = "mySuperSecretKeyThatIsVeryLongSoThatItDoesNotCreateAProblemOfAnyKindToMe";

    public String generateToken(String id,String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(id)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .and()
                .signWith(getKey())
                .compact();

    }


    private SecretKey getKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){

       Token token_status = tokenStatus(token);

       if(!token_status.isValid() || token_status.isExpired()){
           return "Invalid token";
       }

        return extractClaim(token, Claims::getSubject);
    }
    public String extractRole(String token){

        Token token_status = tokenStatus(token);
//        System.out.println(token_status);

        if(!token_status.isValid() || token_status.isExpired()){
            return "Invalid token";
        }

        final Claims claim = extractAllClaims(token);
        return claim.get("role", String.class);
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean invalidToken(String token){

        if(isTokenExpired(token)){
            return true;
        }

        try{
            Claims claims = extractAllClaims(token);
        }
        catch(SignatureException e){
//            System.out.println(e.getMessage());
            return true;
        }

        return false;
    }

    public boolean isTokenExpired(String token){

        try{
            return extractExpiration(token).before(new Date());
        }
        catch(ExpiredJwtException | SignatureException e){
            return true;
        }
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public Token tokenStatus(String token){
        Token token_status = new Token();
        token_status.setToken(token);
//        token_status.setExpired(false);
        token_status.setValid(true);
        try{
            Claims claims = extractAllClaims(token);
        }
        catch(SignatureException | MalformedJwtException e){
            token_status.setToken(token);
            token_status.setValid(false);
//            System.out.println("cought signature exception");
            return token_status;
        }
        catch(ExpiredJwtException e){
            token_status.setToken(token);
            token_status.setExpired(true);
            token_status.setValid(true);
//            System.out.println("cought expired jwt");
        }
        return token_status;
    }

    private Claims extractExpiredClaims(String token){
        try{
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseUnsecuredClaims(token)
                    .getPayload();
        }
        catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public String getRefreshToken(String token){
        Claims claims = extractExpiredClaims(token);
//        System.out.println(claims.getSubject());
//        System.out.println(claims.get("role", String.class));

        return generateToken(claims.getSubject(), claims.get("role", String.class));
    }


}
