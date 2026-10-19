package com.caresync.backend.appointment;

import com.caresync.backend.common.AppointmentStatus;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.UUID;

public record CreateAppointmentRequest(
        @NotNull UUID patientId,
        @NotNull UUID userId,
        @NotBlank @Size(min = 2) String primaryPhysician,
        @NotBlank @Size(min = 2, max = 500) String reason,
        @NotNull Instant schedule,
        @NotNull AppointmentStatus status,
        @Size(max = 500) String note
) {
}
