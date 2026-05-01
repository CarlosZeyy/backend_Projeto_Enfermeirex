package dev.carlosmoises.projeto.enferm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "medication_dosage")
    private String medicationDosage;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
