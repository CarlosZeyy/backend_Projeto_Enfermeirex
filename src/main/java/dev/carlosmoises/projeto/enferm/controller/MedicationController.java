package dev.carlosmoises.projeto.enferm.controller;

import dev.carlosmoises.projeto.enferm.DTO.CreateMedicationDTO;
import dev.carlosmoises.projeto.enferm.DTO.MedicationResponseDTO;
import dev.carlosmoises.projeto.enferm.service.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationService medicationService;

    MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> createMedication(@RequestBody CreateMedicationDTO createMedicationDTO) {
        var medicationId = medicationService.createMedicationNotation(createMedicationDTO);

        var response = new ResponseMessage("Anotação de medicamento criada com sucesso.");

        return ResponseEntity.created(URI.create("/medications/" + medicationId)).body(response);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicationResponseDTO>> getPatientMedication(@PathVariable("patientId") Long patientId) {
        var medications = medicationService.getPatientMedicationById(patientId);

        return ResponseEntity.ok(medications);
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<Void> deleteMedication(@PathVariable("medicationId") Long id) {
        medicationService.deleteMedicationById(id);
        return ResponseEntity.noContent().build();
    }
}
