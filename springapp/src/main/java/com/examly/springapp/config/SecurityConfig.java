package com.examly.springapp.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

 
 
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor-based injection
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, 
                          UserDetailsService userDetailsService, 
                          PasswordEncoder passwordEncoder, 
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


     @Autowired
    public void configure(AuthenticationManagerBuilder authority)throws Exception{
        authority.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
 
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder)
        .and()
        .build();
    }
 
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
        .cors(cors->cors.disable())
        .authorizeHttpRequests(auth->auth
        .requestMatchers(HttpMethod.GET, "/api/properties").hasAnyRole("USER","ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/properties").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/api/properties/{propertyId}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/properties/{propertyId}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/feedback").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET, "/api/feedback/user/{userId}").hasAnyRole("USER")
        .requestMatchers(HttpMethod.DELETE,"/api/feedback/{feedbackId}").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET,"/api/feedback").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/feedback/user/{userId}").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET,"/api/feedback/{feedbackId}").hasAnyRole("USER","ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/inquiries").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET,"/api/inquiries/user/{userId}").hasAnyRole("USER")
        .requestMatchers(HttpMethod.GET,"/api/inquiries").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/api/inquiries/{inquiryId}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE,"/api/inquiries/{inquiryId}").hasAnyRole("ADMIN","USER")
        .anyRequest().permitAll())
        .exceptionHandling(exception-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);    
        return http.build();
    }
 

}