package com.isika.projet3.SmartImplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.isika.projet3.SmartImplant.models.Dentist;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Integer> {
}