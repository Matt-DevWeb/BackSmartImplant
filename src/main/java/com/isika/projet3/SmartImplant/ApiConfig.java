package com.isika.projet3.SmartImplant;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isika.projet3.SmartImplant.models.Patient;

@RestController
@RequestMapping("/api")
public class ApiConfig {

    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getPatients() {
        // Logique métier
        List<Patient> patients = List.of(); // initialize your list of patients here
        return ResponseEntity.ok(patients);
    }

}
