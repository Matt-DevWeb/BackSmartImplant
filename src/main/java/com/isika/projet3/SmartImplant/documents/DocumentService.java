package com.isika.projet3.SmartImplant.documents;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentPatient saveDocument(DocumentPatient document) {
        return documentRepository.save(document);
    }

    public List<DocumentPatient> getDocumentsByPatientId(UUID patientId) {
        return documentRepository.findByPatientId(patientId);

    }

    public List<DocumentPatient> getDocumentsByTreatmentId(UUID treatmentId) {
        return documentRepository.findByTreatmentId(treatmentId);
    }

    public List<DocumentPatient> getDocumentsByIds(List<UUID> ids) {
        return documentRepository.findAllById(ids);
    }

    public void deleteDocument(UUID id) {
        documentRepository.deleteById(id);
    }
}
