package dev.carlosmoises.projeto.enferm.DTO;

import dev.carlosmoises.projeto.enferm.model.Patient;

public record PatientResponseDTO(Long id, String name, String phone, String address, String obs) {
    public PatientResponseDTO(Patient patient) {
        this(patient.getPatientId(), patient.getName(), patient.getPhone(), patient.getAddress(), patient.getObs());
    }
}