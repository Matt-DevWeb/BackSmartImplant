package com.isika.projet3.SmartImplant.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isika.projet3.SmartImplant.documents.DocumentRepository;
import com.isika.projet3.SmartImplant.documents.DocumentPatient;
import com.isika.projet3.SmartImplant.dto.TreatmentRecordDTO;
import com.isika.projet3.SmartImplant.models.Dentist;
import com.isika.projet3.SmartImplant.models.Patient;
import com.isika.projet3.SmartImplant.treatmentrecords.TreatmentRecord;
import com.isika.projet3.SmartImplant.repository.DentistRepository;
import com.isika.projet3.SmartImplant.repository.PatientRepository;
import com.isika.projet3.SmartImplant.repository.TreatmentRecordRepository;

@Service
public class TreatmentRecordService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TreatmentRecordRepository treatmentRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    public List<TreatmentRecord> getAllTreatmentRecords() {
        return treatmentRecordRepository.findAll();
    }

    public Optional<TreatmentRecord> getTreatmentRecordById(UUID id) {
        return treatmentRecordRepository.findById(id);
    }

    public List<TreatmentRecord> getTreatmentRecordsByPatient(Integer patientId) {
        return treatmentRecordRepository.findByPatientId(patientId);
    }

    public List<TreatmentRecord> getTreatmentRecordsByDentist(Integer dentistId) {
        return treatmentRecordRepository.findByDentistId(dentistId);
    }

    public List<TreatmentRecord> getTreatmentRecordsByDentistAndDate(Integer dentistId, LocalDateTime date) {
        return treatmentRecordRepository.findByDentistIdAndDate(dentistId, date);
    }

    public List<TreatmentRecord> getPendingTreatmentsByDentist(Integer dentistId) {
        return treatmentRecordRepository.findByDentistIdAndCompletedFalse(dentistId);
    }

    public TreatmentRecord createTreatmentRecord(TreatmentRecordDTO recordDTO) {
        TreatmentRecord record = convertDtoToEntity(recordDTO);
        return treatmentRecordRepository.save(record);
    }

    public Optional<TreatmentRecord> updateTreatmentRecord(UUID id, TreatmentRecordDTO recordDTO) {
        return treatmentRecordRepository.findById(id)
                .map(record -> {
                    if (recordDTO.getTreatmentDateTime() != null) {
                        record.setTreatmentDateTime(recordDTO.getTreatmentDateTime());
                    }
                    if (recordDTO.getInterventionType() != null) {
                        record.setInterventionType(recordDTO.getInterventionType());
                    }
                    if (recordDTO.getTreatedTeeth() != null) {
                        record.setTreatedTeeth(recordDTO.getTreatedTeeth());
                    }
                    if (recordDTO.getComments() != null) {
                        record.setComments(recordDTO.getComments());
                    }
                    if (recordDTO.getMedications() != null) {
                        record.setPrescribedMedications(recordDTO.getMedications());
                    }

                    if (recordDTO.getDocuments() != null) {
                        List<DocumentPatient> currentDocs = documentRepository.findByTreatmentId(id);

                        for (UUID docId : recordDTO.getDocuments()) {
                            if (currentDocs.stream().noneMatch(doc -> doc.getId().equals(docId))) {
                                documentRepository.findById(docId).ifPresent(doc -> {
                                    doc.setTreatmentId(id);
                                    documentRepository.save(doc);
                                });
                            }
                        }

                        for (DocumentPatient doc : currentDocs) {
                            if (!recordDTO.getDocuments().contains(doc.getId())) {
                                doc.setTreatmentId(null);
                                documentRepository.save(doc);
                            }
                        }
                    }

                    record.setCompleted(recordDTO.isCompleted());

                    return treatmentRecordRepository.save(record);
                });
    }

    public boolean deleteTreatmentRecord(UUID id) {
        return treatmentRecordRepository.findById(id)
                .map(record -> {
                    treatmentRecordRepository.delete(record);
                    return true;
                })
                .orElse(false);
    }

    private TreatmentRecord convertDtoToEntity(TreatmentRecordDTO dto) {
        TreatmentRecord record = new TreatmentRecord();

        if (dto.getId() != null) {
            record.setId(dto.getId());
        }

        if (dto.getPatientId() != null) {
            Optional<Patient> patient = patientRepository.findById(dto.getPatientId());
            patient.ifPresent(record::setPatient);
        }

        if (dto.getDentistId() != null) {
            Optional<Dentist> dentist = dentistRepository.findById(dto.getDentistId());
            dentist.ifPresent(record::setDentist);
        }

        record.setTreatmentDateTime(dto.getTreatmentDateTime());
        record.setInterventionType(dto.getInterventionType());
        record.setTreatedTeeth(dto.getTreatedTeeth());
        record.setComments(dto.getComments());
        record.setPrescribedMedications(dto.getMedications());
        record.setCompleted(dto.isCompleted());

        return record;
    }

    public TreatmentRecordDTO convertEntityToDto(TreatmentRecord entity) {
        TreatmentRecordDTO dto = new TreatmentRecordDTO();

        dto.setId(entity.getId());

        if (entity.getPatient() != null) {
            dto.setPatientId(entity.getPatient().getId());
        }

        if (entity.getDentist() != null) {
            dto.setDentistId(entity.getDentist().getId());
        }

        dto.setTreatmentDateTime(entity.getTreatmentDateTime());
        dto.setInterventionType(entity.getInterventionType());
        dto.setTreatedTeeth(entity.getTreatedTeeth());
        dto.setComments(entity.getComments());
        dto.setMedications(entity.getPrescribedMedications());
        dto.setCompleted(entity.isCompleted());

        List<DocumentPatient> documents = documentRepository.findByTreatmentId(entity.getId());
        List<UUID> docIds = documents.stream()
                .map(DocumentPatient::getId)
                .collect(Collectors.toList());
        dto.setDocuments(docIds);

        return dto;
    }
}