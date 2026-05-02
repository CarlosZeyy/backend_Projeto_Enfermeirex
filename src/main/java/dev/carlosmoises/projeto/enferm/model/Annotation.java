package dev.carlosmoises.projeto.enferm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "annotations")
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annotationId;

    @Column(name = "annotation", columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
