package com.msvc.healt.infraestructer.adapter.treatment.in.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TreatmentResponseDto(
        Long idTreatment,
        LocalDateTime registrationDate,
        LocalDate dateInit,
        LocalDate dateEnd,
        String observations,
        Long consultationId) {
}
