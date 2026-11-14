package com.msvc.healt.infraestructer.adapter.pet.in.rest.dto;

import java.time.LocalDate;

import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Sex;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetWithOwnerRequestDto(
        @NotBlank String name,
        @NotBlank String species,
        String breed,
        @NotNull Sex sex,
        @NotNull LocalDate dateBorn,
        @NotNull @Valid OwnerDataDto owner) {

    public record OwnerDataDto(
            @NotBlank String documentNumer,
            @NotNull DocumentType documentType,
            @NotBlank String name,
            @NotBlank String lastName,
            @NotBlank String email,
            @NotBlank String numberPhone) {
    }
}
