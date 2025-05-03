package com.examly.springapp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
 
import com.examly.springapp.service.UserServiceImpl;
import com.examly.springapp.service.UserServiceImpl;
 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
 
    @Autowired
    JwtUtils jwtUtils;
 
    @Autowired
    private UserServiceImpl service;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	System.out.println("HI");   // Debugging statement to check filter execution
        String token=jwtUtils.extractToken(request);
        System.out.println("token:"+token); // Debugging statement to display token
        // Validate token and set authentication context
        if(token!=null&&jwtUtils.validateToken(token)){
            String username=jwtUtils.extractUsername(token);
            UserDetails userDetails=service.loadUserByUsername(username);
              // Create authentication token and set security context
            UsernamePasswordAuthenticationToken authentication=
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Continue filter chain execution
        filterChain.doFilter(request, response);
    }
   
}