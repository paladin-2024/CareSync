package com.caresync.backend.appointment;

import com.caresync.backend.common.AppointmentStatus;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record UpdateAppointmentRequest(
        String primaryPhysician,
        Instant schedule,
        @Size(max = 500) String reason,
        @Size(max = 500) String note,
        AppointmentStatus status,
        @Size(max = 500) String cancellationReason
) {
}
