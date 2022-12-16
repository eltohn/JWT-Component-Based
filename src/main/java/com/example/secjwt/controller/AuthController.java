package com.example.secjwt.controller;

import com.example.secjwt.config.JwtUtils;
import com.example.secjwt.dto.AuthenticationRequest;
import com.example.secjwt.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final JwtUtils jwtUtils;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                                                      authenticationRequest.getPassword()));
       final UserDetails user = userRepo.findUserByEmail(authenticationRequest.getEmail());
       if(user != null){
           return  ResponseEntity.ok(jwtUtils.generateToken(user));
       }

       return ResponseEntity.status(400).body("some error happened");
    }
}
