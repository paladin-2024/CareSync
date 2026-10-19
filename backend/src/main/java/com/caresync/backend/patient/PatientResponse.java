package com.caresync.backend.patient;

import com.caresync.backend.common.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponse(
        UUID id,
        UUID userId,
        String name,
        String email,
        String phone,
        LocalDate birthDate,
        Gender gender,
        String address,
        String occupation,
        String primaryPhysician,
        String insuranceProvider,
        String insurancePolicyNumber
) {
    public static PatientResponse from(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getUserId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getOccupation(),
                patient.getPrimaryPhysician(),
                patient.getInsuranceProvider(),
                patient.getInsurancePolicyNumber()
        );
    }
}
