package com.isika.projet3.SmartImplant.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("DENTIST")
public class Dentist extends User {

    @OneToMany(mappedBy = "dentist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Patient> patients;

    // Getters et Setters

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
