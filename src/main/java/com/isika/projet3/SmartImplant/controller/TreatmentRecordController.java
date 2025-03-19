package com.isika.projet3.SmartImplant.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isika.projet3.SmartImplant.dto.TreatmentRecordDTO;
import com.isika.projet3.SmartImplant.treatmentrecords.TreatmentRecord;
import com.isika.projet3.SmartImplant.services.TreatmentRecordService;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentRecordController {

    @Autowired
    private TreatmentRecordService treatmentRecordService;

    @GetMapping
    public ResponseEntity<List<TreatmentRecordDTO>> getAllTreatmentRecords() {
        List<TreatmentRecordDTO> treatments = treatmentRecordService.getAllTreatmentRecords().stream()
                .map(treatmentRecordService::convertEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentRecordDTO> getTreatmentRecord(@PathVariable UUID id) {
        return treatmentRecordService.getTreatmentRecordById(id)
                .map(treatmentRecordService::convertEntityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<TreatmentRecordDTO>> getTreatmentRecordsByPatient(@PathVariable Integer patientId) {
        List<TreatmentRecordDTO> treatments = treatmentRecordService.getTreatmentRecordsByPatient(patientId).stream()
                .map(treatmentRecordService::convertEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/dentist/{dentistId}")
    public ResponseEntity<List<TreatmentRecordDTO>> getTreatmentRecordsByDentist(
            @PathVariable Integer dentistId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<TreatmentRecord> treatments;
        if (date != null) {
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
            treatments = treatmentRecordService.getTreatmentRecordsByDentistAndDate(dentistId, dateTime);
        } else {
            treatments = treatmentRecordService.getTreatmentRecordsByDentist(dentistId);
        }

        List<TreatmentRecordDTO> treatmentDTOs = treatments.stream()
                .map(treatmentRecordService::convertEntityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(treatmentDTOs);
    }

    @GetMapping("/dentist/{dentistId}/pending")
    public ResponseEntity<List<TreatmentRecordDTO>> getPendingTreatmentsByDentist(@PathVariable Integer dentistId) {
        List<TreatmentRecordDTO> treatments = treatmentRecordService.getPendingTreatmentsByDentist(dentistId).stream()
                .map(treatmentRecordService::convertEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treatments);
    }

    @PostMapping
    public ResponseEntity<TreatmentRecordDTO> createTreatmentRecord(@RequestBody TreatmentRecordDTO treatmentDTO) {
        TreatmentRecord treatment = treatmentRecordService.createTreatmentRecord(treatmentDTO);
        return ResponseEntity.ok(treatmentRecordService.convertEntityToDto(treatment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentRecordDTO> updateTreatmentRecord(
            @PathVariable UUID id,
            @RequestBody TreatmentRecordDTO treatmentDTO) {

        Optional<TreatmentRecord> updatedTreatment = treatmentRecordService.updateTreatmentRecord(id, treatmentDTO);

        return updatedTreatment
                .map(treatmentRecordService::convertEntityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatmentRecord(@PathVariable UUID id) {
        boolean isDeleted = treatmentRecordService.deleteTreatmentRecord(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}