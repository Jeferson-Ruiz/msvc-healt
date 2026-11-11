package com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner;

import java.time.LocalDate;
import java.util.Set;
import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Pet;

public record OwnerRequestDto(
        String name,
        String lastName,
        DocumentType documentType,
        String documentNumer,
        String email,
        String numberPhone,
        boolean status,
        Set<Pet> pets,
        LocalDate registrationDate) {
}