package com.isika.projet3.SmartImplant.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    @ManyToOne
    private Dentist dentist;

    // Getters et Setters
    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }
}
