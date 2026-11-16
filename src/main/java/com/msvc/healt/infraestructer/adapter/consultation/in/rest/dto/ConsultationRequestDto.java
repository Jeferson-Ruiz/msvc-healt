package com.msvc.healt.infraestructer.adapter.consultation.in.rest.dto;

import com.msvc.healt.domain.models.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultationRequestDto(
        @NotNull Long doctorId,
        @NotBlank String personCharge,
        @NotBlank String documentNumber,
        @NotBlank String petName,
        Integer age,
        @NotNull Sex sex,
        @NotBlank String reasonInquiry,
        String diagnosis,
        String observations) {
}
