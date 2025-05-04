package com.examly.springapp.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
 
@Component
public class JwtUtils {
    @Value("${SECRET_KEY}")   // Injects secret key from application properties

    private String SECRET_KEY="java";

    // Generates JWT token using authentication details
    public String generateToken(Authentication authentication) {
        UserDetails userdetails= (UserDetails)authentication.getPrincipal();
        return Jwts.builder()
        .setSubject(userdetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))  // Sets token expiration time (30 minutes)
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Signs the token using HS256 algorithm
        .compact();
    }
    // Extracts the username from the provided JWT token
    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
    public Date extractExpiration(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
    }
    // Checks if the token has expired
    public boolean isTokenExpired(String token){
        Date expireDate=extractExpiration(token);
        return expireDate.before(new Date());
    }
    // Extracts the token from the Authorization header in the HTTP request
    public String extractToken(HttpServletRequest request) {
       String header=request.getHeader("Authorization");
       if(header!=null&&header.startsWith("Bearer ")){
        return header.substring(7);
       }
       return null;
    }
// Validates the JWT token by checking its expiration
    public boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }
}