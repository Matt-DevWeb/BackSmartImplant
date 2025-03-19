package com.isika.projet3.SmartImplant.documents;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends MongoRepository<DocumentPatient, UUID> {
    List<DocumentPatient> findByPatientId(UUID patientId);

    List<DocumentPatient> findByTreatmentId(UUID treatmentId);
}