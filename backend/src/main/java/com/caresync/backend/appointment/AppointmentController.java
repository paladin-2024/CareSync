package com.caresync.backend.appointment;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse create(@Valid @RequestBody CreateAppointmentRequest request) {
        return AppointmentResponse.from(appointmentService.create(request));
    }

    @GetMapping("/{id}")
    public AppointmentResponse get(@PathVariable UUID id) {
        return AppointmentResponse.from(appointmentService.get(id));
    }

    @GetMapping
    public List<AppointmentResponse> listByUser(@RequestParam UUID userId) {
        return appointmentService.listByUser(userId).stream().map(AppointmentResponse::from).toList();
    }

    @PatchMapping("/{id}")
    public AppointmentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateAppointmentRequest request) {
        return AppointmentResponse.from(appointmentService.update(id, request));
    }
}
