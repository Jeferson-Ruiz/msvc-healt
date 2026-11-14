package com.msvc.healt.infraestructer.adapter.pet.in.rest.dto;

import java.time.LocalDate;
import com.msvc.healt.domain.models.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRequestDto(
        @NotBlank String name,
        @NotBlank String species,
        String breed,
        @NotNull Sex sex,
        @NotNull LocalDate dateBorn,
        @NotBlank String ownerDocumentNumber) {
}
