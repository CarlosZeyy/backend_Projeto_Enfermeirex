package dev.carlosmoises.projeto.enferm.controller;

import dev.carlosmoises.projeto.enferm.DTO.AnnotationResponseDTO;
import dev.carlosmoises.projeto.enferm.DTO.CreateAnnotationDTO;
import dev.carlosmoises.projeto.enferm.service.AnnotationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    private final AnnotationService annotationService;

    AnnotationController(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> createAnnotation(@RequestBody CreateAnnotationDTO createAnnotationDTO) {
        var annotationId = annotationService.createAnnotation(createAnnotationDTO);

        var response = new ResponseMessage("Anotação criada com sucesso.");

        return ResponseEntity.created(URI.create("/annotation/" + annotationId)).body(response);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AnnotationResponseDTO>> getPatientAnnotations(@PathVariable("patientId") Long patientId) {
        var annotations = annotationService.getPatientAnnotations(patientId);

        return ResponseEntity.ok(annotations);
    }
}
