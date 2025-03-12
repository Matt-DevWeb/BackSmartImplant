package com.isika.projet3.SmartImplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isika.projet3.SmartImplant.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}