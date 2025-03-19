package com.isika.projet3.SmartImplant.controller;

import com.isika.projet3.SmartImplant.documents.DocumentPatient;
import com.isika.projet3.SmartImplant.documents.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/treatments/{treatmentId}/documents")
    public ResponseEntity<DocumentPatient> uploadDocument(
            @PathVariable UUID treatmentId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("patientId") Integer patientId,
            @RequestParam("documentType") String documentType) {

        try {
            DocumentPatient document = new DocumentPatient();
            document.setId(UUID.randomUUID());
            document.setName(file.getOriginalFilename());
            document.setContentType(file.getContentType());
            document.setData(file.getBytes());
            document.setPatientId(patientId);
            document.setTreatmentId(treatmentId);
            document.setDocumentType(documentType);

            DocumentPatient savedDoc = documentService.saveDocument(document);

            // On ne renvoie pas les données binaires dans la réponse pour économiser de la
            // bande passante
            DocumentPatient responseDoc = new DocumentPatient();
            responseDoc.setId(savedDoc.getId());
            responseDoc.setName(savedDoc.getName());
            responseDoc.setContentType(savedDoc.getContentType());
            responseDoc.setPatientId(savedDoc.getPatientId());
            responseDoc.setTreatmentId(savedDoc.getTreatmentId());
            responseDoc.setDocumentType(savedDoc.getDocumentType());

            return ResponseEntity.ok(responseDoc);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/treatments/{treatmentId}/documents")
    public ResponseEntity<List<DocumentPatient>> getDocumentsByTreatment(@PathVariable UUID treatmentId) {
        List<DocumentPatient> documents = documentService.getDocumentsByTreatmentId(treatmentId);

        // On filtre les données binaires pour la liste
        List<DocumentPatient> filteredDocs = documents.stream().map(doc -> {
            DocumentPatient filteredDoc = new DocumentPatient();
            filteredDoc.setId(doc.getId());
            filteredDoc.setName(doc.getName());
            filteredDoc.setContentType(doc.getContentType());
            filteredDoc.setPatientId(doc.getPatientId());
            filteredDoc.setTreatmentId(doc.getTreatmentId());
            filteredDoc.setDocumentType(doc.getDocumentType());
            return filteredDoc;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(filteredDocs);
    }

    @GetMapping("/documents/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable UUID id) {
        List<DocumentPatient> documents = documentService.getDocumentsByIds(List.of(id));

        if (documents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DocumentPatient document = documents.get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(document.getContentType()));
        headers.setContentDispositionFormData("attachment", document.getName());

        return new ResponseEntity<>(document.getData(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DocumentPatient>> getDocumentsByPatientId(@PathVariable UUID patientId) {
        List<DocumentPatient> documents = documentService.getDocumentsByPatientId(patientId);

        // Filter out binary data for the list
        List<DocumentPatient> filteredDocs = documents.stream().map(doc -> {
            DocumentPatient filteredDoc = new DocumentPatient();
            filteredDoc.setId(doc.getId());
            filteredDoc.setName(doc.getName());
            filteredDoc.setContentType(doc.getContentType());
            filteredDoc.setPatientId(doc.getPatientId());
            filteredDoc.setTreatmentId(doc.getTreatmentId());
            filteredDoc.setDocumentType(doc.getDocumentType());
            return filteredDoc;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(filteredDocs);
    }
}