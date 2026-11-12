package com.msvc.healt.infraestructer.adapter.owner.in.rest.mapper;

import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner.OwnerResponseDto;

public class OwnerMapper {

    public static OwnerResponseDto toDto(Owner owner) {
        return new OwnerResponseDto(
                owner.getId(),
                owner.getName().value(),
                owner.getLastName().value(),
                owner.getDocumentType(),
                owner.getDocumentNumer().value(),
                owner.getEmail().value(),
                owner.getNumberPhone().value(),
                owner.isStatus());
    }
}
