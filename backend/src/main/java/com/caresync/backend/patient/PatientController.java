package com.caresync.backend.patient;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse register(@Valid @RequestBody RegisterPatientRequest request) {
        return PatientResponse.from(patientService.register(request));
    }

    @GetMapping("/{id}")
    public PatientResponse get(@PathVariable UUID id) {
        return PatientResponse.from(patientService.get(id));
    }
}
