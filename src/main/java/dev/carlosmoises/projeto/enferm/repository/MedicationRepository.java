package dev.carlosmoises.projeto.enferm.repository;

import dev.carlosmoises.projeto.enferm.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByPatientId(Long patientId);
}
