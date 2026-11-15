package com.caresync.backend.patient;

import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/{id}/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PatientResponse uploadDocument(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        return PatientResponse.from(patientService.uploadDocument(id, file));
    }

    @GetMapping("/{id}/document")
    public ResponseEntity<Resource> downloadDocument(@PathVariable UUID id) {
        Patient patient = patientService.get(id);
        Resource resource = patientService.loadDocument(id);
        MediaType contentType = patient.getIdentificationDocumentContentType() != null
                ? MediaType.parseMediaType(patient.getIdentificationDocumentContentType())
                : MediaType.APPLICATION_OCTET_STREAM;
        return ResponseEntity.ok().contentType(contentType).body(resource);
    }
}
