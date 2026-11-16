package com.msvc.healt.infraestructer.adapter.consultation;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.domain.port.in.ConsultationUseCase;
import com.msvc.healt.infraestructer.adapter.consultation.in.rest.dto.ConsultationRequestDto;
import com.msvc.healt.infraestructer.adapter.consultation.in.rest.dto.ConsultationResponseDto;
import com.msvc.healt.infraestructer.adapter.consultation.in.rest.mapper.ConsultationMapper;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationUseCase consultationUseCase;

    public ConsultationController(ConsultationUseCase consultationUseCase) {
        this.consultationUseCase = consultationUseCase;
    }

    @PostMapping
    public ResponseEntity<ConsultationResponseDto> create(
            @RequestBody @Valid ConsultationRequestDto consultationRequestDto) {
        Consultation consultation = consultationUseCase.create(ConsultationMapper.toModel(consultationRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ConsultationMapper.toDto(consultation));
    }

    @GetMapping
    public ResponseEntity<List<ConsultationResponseDto>> getAll() {
        return ResponseEntity.ok(consultationUseCase.searchAll().stream().map(ConsultationMapper::toDto).toList());
    }

    @GetMapping("/pet")
    public ResponseEntity<List<ConsultationResponseDto>> getByPet(@RequestParam("id") String id) {
        return ResponseEntity.ok(consultationUseCase.searchByPet(id).stream().map(ConsultationMapper::toDto).toList());
    }

    @GetMapping("/owner")
    public ResponseEntity<List<ConsultationResponseDto>> getByOwner(@RequestParam("number") String number) {
        return ResponseEntity
                .ok(consultationUseCase.searchByOwner(number).stream().map(ConsultationMapper::toDto).toList());
    }

    @GetMapping("/date")
    public ResponseEntity<List<ConsultationResponseDto>> getByDate(@RequestParam("init") LocalDate init,
            @RequestParam(value = "end", required = false) LocalDate end) {
        return ResponseEntity
                .ok(consultationUseCase.searchByDate(init, end).stream().map(ConsultationMapper::toDto).toList());
    }
}
