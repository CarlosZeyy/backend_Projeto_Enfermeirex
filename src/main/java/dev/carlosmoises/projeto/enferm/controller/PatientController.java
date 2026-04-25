package dev.carlosmoises.projeto.enferm.controller;

import dev.carlosmoises.projeto.enferm.DTO.CreatePatientDTO;
import dev.carlosmoises.projeto.enferm.DTO.PatientResponseDTO;
import dev.carlosmoises.projeto.enferm.DTO.UpdatePatientDTO;
import dev.carlosmoises.projeto.enferm.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createPatient(@Valid @RequestBody CreatePatientDTO createPatientDTO) {
        var patientId = patientService.createPatient(createPatientDTO);

        var response = new ResponseMessage("Paciente cadastrado com sucesso.");

        return ResponseEntity.created(URI.create("/pacientes/" + patientId)).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var patientsPage = patientService.getAllPatients(pageable);

        return ResponseEntity.ok(patientsPage);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatient(@PathVariable("patientId") Long patientId) {
        var patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("patientId") Long patientId, @RequestBody UpdatePatientDTO updatePatientDTO) {
        var updatedPatient = patientService.updatePatientById(patientId, updatePatientDTO);
        return ResponseEntity.ok(updatedPatient);
    }
}
