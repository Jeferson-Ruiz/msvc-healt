package com.msvc.healt.infraestructer.adapter.pet.in.rest.dto;

import java.time.LocalDate;
import com.msvc.healt.domain.models.Sex;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner.OwnerResponseDto;

public record PetResponseDto(
        Long id,
        String name,
        String species,
        String breed,
        Sex sex,
        LocalDate dateBorn,
        LocalDate registrationDate,
        Integer age,
        OwnerResponseDto owner) {
}
