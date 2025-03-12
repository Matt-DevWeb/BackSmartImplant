package com.isika.projet3.SmartImplant.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isika.projet3.SmartImplant.models.Dentist;
import com.isika.projet3.SmartImplant.services.DentistService;

@RestController
@RequestMapping("/api")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping("/dentists")
    public ResponseEntity<List<Dentist>> getDentists() {
        List<Dentist> dentists = dentistService.getAllDentists();
        return ResponseEntity.ok(dentists);
    }

    @GetMapping("/dentists/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable Integer id) {
        Optional<Dentist> dentist = dentistService.getDentistById(id);
        return dentist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/dentists")
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist dentist) {
        Dentist createdDentist = dentistService.createDentist(dentist);
        return ResponseEntity.ok(createdDentist);
    }

    @PutMapping("/dentists/{id}")
    public ResponseEntity<Dentist> updateDentist(@PathVariable Integer id, @RequestBody Dentist dentistDetails) {
        Optional<Dentist> updatedDentist = dentistService.updateDentist(id, dentistDetails);
        return updatedDentist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/dentists/{id}")
    public ResponseEntity<Void> deleteDentist(@PathVariable Integer id) {
        boolean isDeleted = dentistService.deleteDentist(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}