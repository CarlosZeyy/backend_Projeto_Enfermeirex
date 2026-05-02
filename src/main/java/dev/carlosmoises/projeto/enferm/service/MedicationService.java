package dev.carlosmoises.projeto.enferm.service;

import dev.carlosmoises.projeto.enferm.DTO.CreateMedicationDTO;
import dev.carlosmoises.projeto.enferm.DTO.MedicationResponseDTO;
import dev.carlosmoises.projeto.enferm.model.Medication;
import dev.carlosmoises.projeto.enferm.repository.MedicationRepository;
import dev.carlosmoises.projeto.enferm.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;

    public MedicationService(MedicationRepository medicationRepository, PatientRepository patientRepository) {
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
    }

    public Long createMedicationNotation(CreateMedicationDTO createMedicationDTO) {
        var patient = patientRepository.findById(createMedicationDTO.patientId()).orElseThrow(() -> new RuntimeException("Id de paciente não encontrado"));

        var medication = new Medication();

        medication.setPatient(patient);
        medication.setMedicationName(createMedicationDTO.medicationName());
        medication.setMedicationDosage(createMedicationDTO.medicationDosage());

        var medicationSaved = medicationRepository.save(medication);

        return medicationSaved.getMedicationId();
    }

    public List<MedicationResponseDTO> getPatientMedicationById(Long patientId) {
        return medicationRepository.findByPatientId(patientId).stream().map(
                (medication) -> new MedicationResponseDTO(
                        medication.getMedicationId(),
                        medication.getPatient().getId(),
                        medication.getMedicationName(),
                        medication.getMedicationDosage()
                )
        ).toList();
    }

    public void deleteMedicationById(Long id) {
        medicationRepository.deleteById(id);
    }
}
