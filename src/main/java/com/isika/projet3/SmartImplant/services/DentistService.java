package com.isika.projet3.SmartImplant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isika.projet3.SmartImplant.models.Dentist;
import com.isika.projet3.SmartImplant.repository.DentistRepository;

@Service
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    public Optional<Dentist> getDentistById(Integer id) {
        return dentistRepository.findById(id);
    }

    public Dentist createDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    public Optional<Dentist> updateDentist(Integer id, Dentist dentistDetails) {
        try {
            return dentistRepository.findById(id).map(dentist -> {
                // Mise à jour conditionnelle des champs
                if (dentistDetails.getName() != null) {
                    dentist.setName(dentistDetails.getName());
                }
                if (dentistDetails.getFirstName() != null) {
                    dentist.setFirstName(dentistDetails.getFirstName());
                }
                if (dentistDetails.getEmail() != null) {
                    dentist.setEmail(dentistDetails.getEmail());
                }
                if (dentistDetails.getPassword() != null && !dentistDetails.getPassword().isEmpty()) {
                    dentist.setPassword(dentistDetails.getPassword());
                }
                if (dentistDetails.getRole() != null) {
                    dentist.setRole(dentistDetails.getRole());
                }

                // Mise à jour des champs spécifiques au dentiste
                if (dentistDetails.getPhone() != null) {
                    dentist.setPhone(dentistDetails.getPhone());
                }
                if (dentistDetails.getAddress() != null) {
                    dentist.setAddress(dentistDetails.getAddress());
                }
                if (dentistDetails.getLicenseNumber() != null) {
                    dentist.setLicenseNumber(dentistDetails.getLicenseNumber());
                }
                if (dentistDetails.getSpecialization() != null) {
                    dentist.setSpecialization(dentistDetails.getSpecialization());
                }
                if (dentistDetails.getClinicName() != null) {
                    dentist.setClinicName(dentistDetails.getClinicName());
                }

                return dentistRepository.save(dentist);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deleteDentist(Integer id) {
        return dentistRepository.findById(id).map(dentist -> {
            dentistRepository.delete(dentist);
            return true;
        }).orElse(false);
    }
}