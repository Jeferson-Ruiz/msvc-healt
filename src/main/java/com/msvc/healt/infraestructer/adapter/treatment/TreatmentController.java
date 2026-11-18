package com.msvc.healt.infraestructer.adapter.treatment;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.msvc.healt.domain.models.Treatment;
import com.msvc.healt.domain.port.in.TreatmentUseCase;
import com.msvc.healt.infraestructer.adapter.treatment.in.rest.dto.TreatmentRequestDto;
import com.msvc.healt.infraestructer.adapter.treatment.in.rest.dto.TreatmentResponseDto;
import com.msvc.healt.infraestructer.adapter.treatment.in.rest.mapper.TreatmentMapper;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    private final TreatmentUseCase treatmentUseCase;

    public TreatmentController(TreatmentUseCase treatmentUseCase) {
        this.treatmentUseCase = treatmentUseCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentResponseDto> create(@RequestBody @Valid TreatmentRequestDto treatmentRequestDto) {
        Treatment treatment = treatmentUseCase.create(TreatmentMapper.toModel(treatmentRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(TreatmentMapper.toDto(treatment));
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResponseDto>> getAll() {
        return ResponseEntity.ok(treatmentUseCase.searchAll().stream().map(TreatmentMapper::toDto).toList());
    }

    @GetMapping("/consultation/{id}")
    public ResponseEntity<TreatmentResponseDto> getByConsultation(@PathVariable("id") Long id) {
        return ResponseEntity.ok(TreatmentMapper.toDto(treatmentUseCase.searchByIdConsultation(id)));
    }
}
