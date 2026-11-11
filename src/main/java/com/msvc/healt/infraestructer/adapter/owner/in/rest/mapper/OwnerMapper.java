package com.msvc.healt.infraestructer.adapter.owner.in.rest.mapper;

import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner.OwnerRequestDto;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.dto.owner.OwnerResponseDto;

public class OwnerMapper {

    public static Owner toModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setId(null);
        owner.setName(new NormalizedText(dto.name()));
        owner.setLastName(new NormalizedText(dto.lastName()));
        owner.setDocumentType(dto.documentType());
        owner.setEmail(new Email(dto.email()));
        owner.setNumberPhone(new NormalizedNumber(dto.numberPhone()));
        owner.setStatus(true);
        owner.setPets(dto.pets());
        owner.setRegistrationDate(null);
        return owner;
    }

    public static OwnerResponseDto toDto(Owner owner) {
        return new OwnerResponseDto(
                owner.getId(),
                owner.getName(),
                owner.getLastName(),
                owner.getDocumentType(),
                owner.getDocumentNumer(),
                owner.getEmail(),
                owner.getNumberPhone(),
                owner.isStatus(),
                owner.getPets());
    }
}
