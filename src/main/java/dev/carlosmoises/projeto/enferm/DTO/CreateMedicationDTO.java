package dev.carlosmoises.projeto.enferm.DTO;

public record CreateMedicationDTO(Long patientId, String medicationName, String MedicationDosage) {
}
