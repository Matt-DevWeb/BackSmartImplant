package com.isika.projet3.SmartImplant.treatmentrecords;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.isika.projet3.SmartImplant.models.Dentist;
import com.isika.projet3.SmartImplant.models.Patient;

@Entity
@Table(name = "treatment_records")
public class TreatmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    private LocalDateTime treatmentDateTime;

    private String interventionType; // Type d'intervention réalisée

    @ElementCollection
    private Set<Integer> treatedTeeth = new HashSet<>(); // Numéros des dents traitées

    @Column(length = 2000)
    private String comments; // Commentaires sur l'intervention

    @Column(length = 1000)
    private String prescribedMedications; // Médicaments prescrits

    private boolean completed = false; // Statut du traitement

    // Getters et Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
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

    public String getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(String prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}