package com.isika.projet3.SmartImplant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isika.projet3.SmartImplant.models.Patient;
import com.isika.projet3.SmartImplant.repository.PatientRepository;
import com.isika.projet3.SmartImplant.repository.DentistRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository; // Ajout de l'injection

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
        try {
            return patientRepository.findById(id).map(patient -> {
                // Mise à jour conditionnelle des champs
                if (patientDetails.getName() != null) {
                    patient.setName(patientDetails.getName());
                }
                if (patientDetails.getFirstName() != null) {
                    patient.setFirstName(patientDetails.getFirstName());
                }
                if (patientDetails.getEmail() != null) {
                    patient.setEmail(patientDetails.getEmail());
                }
                if (patientDetails.getPassword() != null && !patientDetails.getPassword().isEmpty()) {
                    patient.setPassword(patientDetails.getPassword());
                }
                if (patientDetails.getRole() != null) {
                    patient.setRole(patientDetails.getRole());
                }
                if (patientDetails.getAge() != null) {
                    patient.setAge(patientDetails.getAge());
                }

                // CORRECTION: Utilisation de l'instance injectée et non pas l'interface
                // statique
                // Prise en charge plus prudente du dentiste
                if (patientDetails.getDentist() == null) {
                    // Si null, conserver la valeur existante
                } else if (patientDetails.getDentist().getId() != null) {
                    // Si on a juste l'ID, charger l'entité dentiste complète
                    dentistRepository.findById(patientDetails.getDentist().getId())
                            .ifPresent(patient::setDentist);
                }

                // Reste du code inchangé...
                if (patientDetails.getHealthIssues() != null) {
                    patient.setHealthIssues(patientDetails.getHealthIssues());
                }
                if (patientDetails.getSocialSecurityNumber() != null) {
                    patient.setSocialSecurityNumber(patientDetails.getSocialSecurityNumber());
                }
                if (patientDetails.getMutualMembershipNumber() != null) {
                    patient.setMutualMembershipNumber(patientDetails.getMutualMembershipNumber());
                }
                if (patientDetails.getAllergies() != null) {
                    patient.setAllergies(patientDetails.getAllergies());
                }
                if (patientDetails.getCurrentMedications() != null) {
                    patient.setCurrentMedications(patientDetails.getCurrentMedications());
                }
                if (patientDetails.getChronicIllnesses() != null) {
                    patient.setChronicIllnesses(patientDetails.getChronicIllnesses());
                }

                return patientRepository.save(patient);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deletePatient(Integer id) {
        return patientRepository.findById(id).map(patient -> {
            patientRepository.delete(patient);
            return true;
        }).orElse(false);
    }
}