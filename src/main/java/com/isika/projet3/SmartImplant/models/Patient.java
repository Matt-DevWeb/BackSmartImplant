package com.isika.projet3.SmartImplant.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    private Integer age;
    private String healthIssues; // Exemple : "Diabète, hypertension"
    private String socialSecurityNumber; // Exemple : "123456789"
    private String mutualMembershipNumber; // Exemple : "987654321"
    private String allergies; // Exemple : "Arachides, pénicilline"
    private String currentMedications; // Exemple : "Antibiotiques, anticoagulants"
    private String chronicIllnesses; // Exemple : "Asthme, diabète de type 2"

    @ManyToOne
    private Dentist dentist;

    // Getters et Setters
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(String healthIssues) {
        this.healthIssues = healthIssues;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getMutualMembershipNumber() {
        return mutualMembershipNumber;
    }

    public void setMutualMembershipNumber(String mutualMembershipNumber) {
        this.mutualMembershipNumber = mutualMembershipNumber;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }

    public void setCurrentMedications(String currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String getChronicIllnesses() {
        return chronicIllnesses;
    }

    public void setChronicIllnesses(String chronicIllnesses) {
        this.chronicIllnesses = chronicIllnesses;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }
}
