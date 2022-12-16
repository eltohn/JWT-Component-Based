package com.example.secjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> bye(){
        return ResponseEntity.ok("Bye");
    }
}
