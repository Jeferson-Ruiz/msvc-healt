package com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner;

import java.util.Set;

import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Pet;

public record OwnerResponseDto(
        Long id,
        NormalizedText name,
        NormalizedText lastName,
        DocumentType documentType,
        NormalizedNumber documentNumer,
        Email email,
        NormalizedNumber numberPhone,
        boolean status,
        Set<Pet> pets) {
}