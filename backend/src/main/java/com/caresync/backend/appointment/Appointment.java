package com.caresync.backend.appointment;

import com.caresync.backend.common.AppointmentStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "primary_physician", nullable = false, length = 100)
    private String primaryPhysician;

    @Column(nullable = false, length = 500)
    private String reason;

    @Column(nullable = false)
    private Instant schedule;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AppointmentStatus status;

    @Column(length = 500)
    private String note;

    @Column(name = "cancellation_reason", length = 500)
    private String cancellationReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Appointment() {
    }

    public Appointment(UUID patientId, UUID userId, String primaryPhysician, String reason, Instant schedule,
                        AppointmentStatus status, String note) {
        this.patientId = patientId;
        this.userId = userId;
        this.primaryPhysician = primaryPhysician;
        this.reason = reason;
        this.schedule = schedule;
        this.status = status;
        this.note = note;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getPrimaryPhysician() {
        return primaryPhysician;
    }

    public void setPrimaryPhysician(String primaryPhysician) {
        this.primaryPhysician = primaryPhysician;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getSchedule() {
        return schedule;
    }

    public void setSchedule(Instant schedule) {
        this.schedule = schedule;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
