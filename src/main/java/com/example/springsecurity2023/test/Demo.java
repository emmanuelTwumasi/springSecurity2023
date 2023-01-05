package com.example.springsecurity2023.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class Demo {
    @GetMapping
    public ResponseEntity<String> sayhello(){
        return ResponseEntity.ok("this is secured endpoint.");

    }
}
