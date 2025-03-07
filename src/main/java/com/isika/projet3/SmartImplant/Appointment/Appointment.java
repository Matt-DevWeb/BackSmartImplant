package com.isika.projet3.SmartImplant.Appointment;
// package com.isika.projet3.SmartImplant.models;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;

// @Entity
// public class Appointment {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;
// private LocalDateTime date;

// @ManyToOne
// private Dentist dentist;

// @ManyToOne
// private Patient patient;

// public Long getId() {
// return id;
// }

// public void setId(Long id) {
// this.id = id;
// }

// public LocalDateTime getDate() {
// return date;
// }

// public void setDate(LocalDateTime date) {
// this.date = date;
// }

// public Dentist getDentist() {
// return dentist;
// }

// public void setDentist(Dentist dentist) {
// this.dentist = dentist;
// }

// public Patient getPatient() {
// return patient;
// }

// public void setPatient(Patient patient) {
// this.patient = patient;
// }

// }