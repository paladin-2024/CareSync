package com.caresync.backend.patient;

import com.caresync.backend.common.Gender;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false, length = 500)
    private String occupation;

    @Column(name = "emergency_contact_name", nullable = false, length = 50)
    private String emergencyContactName;

    @Column(name = "emergency_contact_number", nullable = false, length = 20)
    private String emergencyContactNumber;

    @Column(name = "primary_physician", nullable = false, length = 100)
    private String primaryPhysician;

    @Column(name = "insurance_provider", nullable = false, length = 50)
    private String insuranceProvider;

    @Column(name = "insurance_policy_number", nullable = false, length = 50)
    private String insurancePolicyNumber;

    @Column(length = 1000)
    private String allergies;

    @Column(name = "current_medication", length = 1000)
    private String currentMedication;

    @Column(name = "family_medical_history", length = 1000)
    private String familyMedicalHistory;

    @Column(name = "past_medical_history", length = 1000)
    private String pastMedicalHistory;

    @Column(name = "identification_type", length = 100)
    private String identificationType;

    @Column(name = "identification_number", length = 100)
    private String identificationNumber;

    @Column(name = "identification_document_url", length = 500)
    private String identificationDocumentUrl;

    @Column(name = "identification_document_filename", length = 255)
    private String identificationDocumentFilename;

    @Column(name = "identification_document_content_type", length = 100)
    private String identificationDocumentContentType;

    @Column(name = "privacy_consent", nullable = false)
    private boolean privacyConsent;

    @Column(name = "treatment_consent", nullable = false)
    private boolean treatmentConsent;

    @Column(name = "disclosure_consent", nullable = false)
    private boolean disclosureConsent;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Patient() {
    }

    public Patient(UUID userId, String name, String email, String phone, LocalDate birthDate, Gender gender,
                   String address, String occupation, String emergencyContactName, String emergencyContactNumber,
                   String primaryPhysician, String insuranceProvider, String insurancePolicyNumber,
                   String allergies, String currentMedication, String familyMedicalHistory, String pastMedicalHistory,
                   String identificationType, String identificationNumber, String identificationDocumentUrl,
                   boolean privacyConsent, boolean treatmentConsent, boolean disclosureConsent) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.occupation = occupation;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.primaryPhysician = primaryPhysician;
        this.insuranceProvider = insuranceProvider;
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.allergies = allergies;
        this.currentMedication = currentMedication;
        this.familyMedicalHistory = familyMedicalHistory;
        this.pastMedicalHistory = pastMedicalHistory;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.identificationDocumentUrl = identificationDocumentUrl;
        this.privacyConsent = privacyConsent;
        this.treatmentConsent = treatmentConsent;
        this.disclosureConsent = disclosureConsent;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public String getPrimaryPhysician() {
        return primaryPhysician;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public String getFamilyMedicalHistory() {
        return familyMedicalHistory;
    }

    public String getPastMedicalHistory() {
        return pastMedicalHistory;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getIdentificationDocumentUrl() {
        return identificationDocumentUrl;
    }

    public void setIdentificationDocumentUrl(String identificationDocumentUrl) {
        this.identificationDocumentUrl = identificationDocumentUrl;
    }

    public String getIdentificationDocumentFilename() {
        return identificationDocumentFilename;
    }

    public void setIdentificationDocumentFilename(String identificationDocumentFilename) {
        this.identificationDocumentFilename = identificationDocumentFilename;
    }

    public String getIdentificationDocumentContentType() {
        return identificationDocumentContentType;
    }

    public void setIdentificationDocumentContentType(String identificationDocumentContentType) {
        this.identificationDocumentContentType = identificationDocumentContentType;
    }

    public boolean isPrivacyConsent() {
        return privacyConsent;
    }

    public boolean isTreatmentConsent() {
        return treatmentConsent;
    }

    public boolean isDisclosureConsent() {
        return disclosureConsent;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
