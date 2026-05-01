package dev.carlosmoises.projeto.enferm.DTO;

public record MedicationResponseDTO(Long medicationId, Long patientId, String medicationName, String medicationDosage) {
}
