package com.isika.projet3.SmartImplant.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isika.projet3.SmartImplant.treatmentrecords.TreatmentRecord;

@Repository
public interface TreatmentRecordRepository extends JpaRepository<TreatmentRecord, UUID> {

    List<TreatmentRecord> findByPatientId(Integer patientId);

    List<TreatmentRecord> findByDentistId(Integer dentistId);

    @Query("SELECT t FROM TreatmentRecord t WHERE t.dentist.id = :dentistId AND DATE(t.treatmentDateTime) = DATE(:date)")
    List<TreatmentRecord> findByDentistIdAndDate(Integer dentistId, LocalDateTime date);

    List<TreatmentRecord> findByDentistIdAndCompletedFalse(Integer dentistId);
}