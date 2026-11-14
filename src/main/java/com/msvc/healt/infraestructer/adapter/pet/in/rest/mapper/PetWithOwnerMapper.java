package com.msvc.healt.infraestructer.adapter.pet.in.rest.mapper;

import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.dto.PetWithOwnerRequestDto;

public class PetWithOwnerMapper {

    public static Pet toModel(PetWithOwnerRequestDto dto) {
        Pet pet = new Pet();
        pet.setIdPet(null);
        pet.setName(new NormalizedText(dto.name()));
        pet.setSpecies(new NormalizedText(dto.species()));
        pet.setBreed(dto.breed() == null ? null : new NormalizedText(dto.breed()));
        pet.setSex(dto.sex());
        pet.setDateBorn(dto.dateBorn());
        pet.setOwner(toOwnerModel(dto.owner()));
        pet.setRegistrationDate(null);
        return pet;
    }

    private static Owner toOwnerModel(PetWithOwnerRequestDto.OwnerDataDto dto) {
        Owner owner = new Owner();
        owner.setId(null);
        owner.setDocumentNumer(new NormalizedNumber(dto.documentNumer()));
        owner.setDocumentType(dto.documentType());
        owner.setName(new NormalizedText(dto.name()));
        owner.setLastName(new NormalizedText(dto.lastName()));
        owner.setEmail(new Email(dto.email()));
        owner.setNumberPhone(new NormalizedNumber(dto.numberPhone()));
        owner.setStatus(true);
        owner.setRegistrationDate(null);
        return owner;
    }
}
