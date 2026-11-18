package com.msvc.healt.infraestructer.adapter.treatment.in.rest.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public record TreatmentRequestDto(
        @NotNull Long consultationId,
        @NotNull LocalDate dateInit,
        @NotNull LocalDate dateEnd,
        String observations) {
}
