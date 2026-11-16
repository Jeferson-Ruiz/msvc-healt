package com.msvc.healt.infraestructer.adapter.consultation.in.rest.dto;

import java.time.LocalDateTime;
import com.msvc.healt.domain.models.Sex;

public record ConsultationResponseDto(
        Long id,
        LocalDateTime registratioDate,
        Long doctorId,
        String personCharge,
        String documentNumber,
        String petName,
        Integer age,
        Sex sex,
        String reasonInquiry,
        String diagnosis,
        String observations,
        Long petId,
        Long ownerId) {
}
