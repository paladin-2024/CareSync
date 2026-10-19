package com.caresync.backend.patient;

import com.caresync.backend.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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
}
