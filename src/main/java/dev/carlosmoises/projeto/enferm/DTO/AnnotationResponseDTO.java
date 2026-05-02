package dev.carlosmoises.projeto.enferm.DTO;

import java.time.LocalDateTime;

public record AnnotationResponseDTO(Long annotationId, Long patientId, String text, LocalDateTime createdAt) {
}
