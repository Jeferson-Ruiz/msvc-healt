package com.msvc.healt.infraestructer.adapter.pet.in.rest.dto;

import java.time.LocalDate;
import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Sex;

public record PetRequestDto(
        String name,
        String species,
        String breed,
        Sex sex,
        LocalDate dateBorn,
        OwnerDataDto owner) {

    public record OwnerDataDto(
            String documentNumer,
            DocumentType documentType,
            String name,
            String lastName,
            String email,
            String numberPhone) {
    }
}
