package com.caresync.backend.appointment;

import com.caresync.backend.common.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID patientId,
        UUID userId,
        String primaryPhysician,
        String reason,
        Instant schedule,
        AppointmentStatus status,
        String note,
        String cancellationReason
) {
    public static AppointmentResponse from(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getUserId(),
                appointment.getPrimaryPhysician(),
                appointment.getReason(),
                appointment.getSchedule(),
                appointment.getStatus(),
                appointment.getNote(),
                appointment.getCancellationReason()
        );
    }
}
