package com.isika.projet3.SmartImplant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PublicController {

    @GetMapping("/all")
    public ResponseEntity<String> getPublicContent() {
        String publicContent = "HOME PAGE";
        return new ResponseEntity<>(publicContent, HttpStatus.OK);
    }
}
