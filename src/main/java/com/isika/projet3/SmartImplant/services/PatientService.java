package com.isika.projet3.SmartImplant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isika.projet3.SmartImplant.models.Patient;
import com.isika.projet3.SmartImplant.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> updatePatient(Integer id, Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(patientDetails.getName());
            patient.setFirstName(patientDetails.getFirstName());
            patient.setEmail(patientDetails.getEmail());
            patient.setPassword(patientDetails.getPassword());
            patient.setRole(patientDetails.getRole());
            patient.setAge(patientDetails.getAge());
            patient.setDentist(patientDetails.getDentist());
            return patientRepository.save(patient);
        });
    }

    public boolean deletePatient(Integer id) {
        return patientRepository.findById(id).map(patient -> {
            patientRepository.delete(patient);
            return true;
        }).orElse(false);
    }
}