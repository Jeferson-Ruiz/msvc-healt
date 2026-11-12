package com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner;

import com.msvc.healt.domain.models.DocumentType;

public record OwnerResponseDto(
        Long id,
        String name,
        String lastName,
        DocumentType documentType,
        String documentNumer,
        String email,
        String numberPhone,
        boolean status) {
}
