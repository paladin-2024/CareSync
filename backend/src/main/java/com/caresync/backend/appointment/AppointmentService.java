package com.caresync.backend.appointment;

import com.caresync.backend.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment create(CreateAppointmentRequest request) {
        Appointment appointment = new Appointment(
                request.patientId(), request.userId(), request.primaryPhysician(), request.reason(),
                request.schedule(), request.status(), request.note()
        );
        return appointmentRepository.save(appointment);
    }

    public Appointment get(UUID id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found: " + id));
    }

    public List<Appointment> listByUser(UUID userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public Appointment update(UUID id, UpdateAppointmentRequest request) {
        Appointment appointment = get(id);
        if (request.primaryPhysician() != null) appointment.setPrimaryPhysician(request.primaryPhysician());
        if (request.schedule() != null) appointment.setSchedule(request.schedule());
        if (request.reason() != null) appointment.setReason(request.reason());
        if (request.note() != null) appointment.setNote(request.note());
        if (request.status() != null) appointment.setStatus(request.status());
        if (request.cancellationReason() != null) appointment.setCancellationReason(request.cancellationReason());
        return appointmentRepository.save(appointment);
    }
}
