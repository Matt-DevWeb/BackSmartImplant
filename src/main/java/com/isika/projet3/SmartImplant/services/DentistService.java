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
        return dentistRepository.findById(id).map(dentist -> {
            dentist.setName(dentistDetails.getName());
            dentist.setFirstName(dentistDetails.getFirstName());
            dentist.setEmail(dentistDetails.getEmail());
            dentist.setPassword(dentistDetails.getPassword());
            dentist.setRole(dentistDetails.getRole());
            // Si d'autres champs spécifiques existent, les mettre à jour ici.
            return dentistRepository.save(dentist);
        });
    }

    public boolean deleteDentist(Integer id) {
        return dentistRepository.findById(id).map(dentist -> {
            dentistRepository.delete(dentist);
            return true;
        }).orElse(false);
    }
}