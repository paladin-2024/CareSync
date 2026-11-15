package com.caresync.backend.patient;

import com.caresync.backend.common.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterPatientRequest(
        @NotNull UUID userId,
        @NotBlank @Size(min = 2, max = 50) String name,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "^\\+\\d{10,15}$", message = "Invalid phone number") String phone,
        @NotNull LocalDate birthDate,
        @NotNull Gender gender,
        @NotBlank @Size(min = 5, max = 500) String address,
        @NotBlank @Size(min = 2, max = 500) String occupation,
        @NotBlank @Size(min = 2, max = 50) String emergencyContactName,
        @NotBlank @Pattern(regexp = "^\\+\\d{10,15}$", message = "Invalid phone number") String emergencyContactNumber,
        @NotBlank @Size(min = 2) String primaryPhysician,
        @NotBlank @Size(min = 2, max = 50) String insuranceProvider,
        @NotBlank @Size(min = 2, max = 50) String insurancePolicyNumber,
        String allergies,
        String currentMedication,
        String familyMedicalHistory,
        String pastMedicalHistory,
        String identificationType,
        String identificationNumber,
        String identificationDocumentUrl,
        @AssertTrue(message = "You must consent to treatment in order to proceed") boolean treatmentConsent,
        @AssertTrue(message = "You must consent to disclosure in order to proceed") boolean disclosureConsent,
        @AssertTrue(message = "You must consent to privacy in order to proceed") boolean privacyConsent
) {
}
