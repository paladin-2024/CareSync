package com.caresync.backend.patient;

import com.caresync.backend.common.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final Path uploadDir;

    public PatientService(PatientRepository patientRepository,
                           @Value("${app.upload-dir:uploads}") String uploadDir) {
        this.patientRepository = patientRepository;
        this.uploadDir = Path.of(uploadDir);
    }

    public Patient register(RegisterPatientRequest request) {
        Patient patient = new Patient(
                request.userId(), request.name(), request.email(), request.phone(), request.birthDate(),
                request.gender(), request.address(), request.occupation(), request.emergencyContactName(),
                request.emergencyContactNumber(), request.primaryPhysician(), request.insuranceProvider(),
                request.insurancePolicyNumber(), request.allergies(), request.currentMedication(),
                request.familyMedicalHistory(), request.pastMedicalHistory(), request.identificationType(),
                request.identificationNumber(), request.identificationDocumentUrl(), request.privacyConsent(),
                request.treatmentConsent(), request.disclosureConsent()
        );
        return patientRepository.save(patient);
    }

    public Patient get(UUID id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found: " + id));
    }

    public Patient uploadDocument(UUID id, MultipartFile file) {
        Patient patient = get(id);
        try {
            Files.createDirectories(uploadDir);
            String storedFilename = id + "-" + System.currentTimeMillis();
            Files.copy(file.getInputStream(), uploadDir.resolve(storedFilename));
            patient.setIdentificationDocumentFilename(storedFilename);
            patient.setIdentificationDocumentContentType(file.getContentType());
            patient.setIdentificationDocumentUrl("/api/patients/" + id + "/document");
        } catch (IOException e) {
            throw new RuntimeException("Failed to store identification document", e);
        }
        return patientRepository.save(patient);
    }

    public Resource loadDocument(UUID id) {
        Patient patient = get(id);
        if (patient.getIdentificationDocumentFilename() == null) {
            throw new NotFoundException("No identification document for patient: " + id);
        }
        return new FileSystemResource(uploadDir.resolve(patient.getIdentificationDocumentFilename()));
    }
}
