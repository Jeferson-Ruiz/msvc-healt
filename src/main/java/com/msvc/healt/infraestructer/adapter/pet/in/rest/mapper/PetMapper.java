package com.msvc.healt.infraestructer.adapter.pet.in.rest.mapper;

import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.infraestructer.adapter.owner.in.rest.mapper.OwnerMapper;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.dto.PetRequestDto;
import com.msvc.healt.infraestructer.adapter.pet.in.rest.dto.PetResponseDto;

public class PetMapper {

    public static Pet toModel(PetRequestDto dto) {
        Pet pet = new Pet();
        pet.setIdPet(null);
        pet.setName(new NormalizedText(dto.name()));
        pet.setSpecies(new NormalizedText(dto.species()));
        pet.setBreed(dto.breed() == null ? null : new NormalizedText(dto.breed()));
        pet.setSex(dto.sex());
        pet.setDateBorn(dto.dateBorn());
        Owner owner = new Owner();
        owner.setDocumentNumer(new NormalizedNumber(dto.ownerDocumentNumber()));
        pet.setOwner(owner);
        pet.setRegistrationDate(null);
        return pet;
    }

    public static PetResponseDto toDto(Pet pet) {
        return new PetResponseDto(
                pet.getIdPet(),
                value(pet.getName()),
                value(pet.getSpecie()),
                value(pet.getBreed()),
                pet.getSex(),
                pet.getDateBorn(),
                pet.getRegistrationDate(),
                pet.getAge(),
                pet.getOwner() == null ? null : OwnerMapper.toDto(pet.getOwner()));
    }

    private static String value(NormalizedText text) {
        return text == null ? null : text.value();
    }
}
