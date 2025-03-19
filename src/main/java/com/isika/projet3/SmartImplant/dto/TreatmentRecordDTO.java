package com.isika.projet3.SmartImplant.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TreatmentRecordDTO {

    private UUID id;
    private Integer patientId;
    private Integer dentistId;
    private LocalDateTime treatmentDateTime;
    private String interventionType;
    private Set<Integer> treatedTeeth;
    private String comments;
    private String medications;
    private boolean completed;
    private List<UUID> documents;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDentistId() {
        return dentistId;
    }

    public void setDentistId(Integer dentistId) {
        this.dentistId = dentistId;
    }

    public LocalDateTime getTreatmentDateTime() {
        return treatmentDateTime;
    }

    public void setTreatmentDateTime(LocalDateTime treatmentDateTime) {
        this.treatmentDateTime = treatmentDateTime;
    }

    public String getInterventionType() {
        return interventionType;
    }

    public void setInterventionType(String interventionType) {
        this.interventionType = interventionType;
    }

    public Set<Integer> getTreatedTeeth() {
        return treatedTeeth;
    }

    public void setTreatedTeeth(Set<Integer> treatedTeeth) {
        this.treatedTeeth = treatedTeeth;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<UUID> getDocuments() {
        return documents;
    }

    public void setDocuments(List<UUID> documents) {
        this.documents = documents;
    }
}