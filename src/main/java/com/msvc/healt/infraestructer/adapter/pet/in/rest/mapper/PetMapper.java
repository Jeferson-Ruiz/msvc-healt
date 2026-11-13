package com.msvc.healt.infraestructer.adapter.pet.in.rest.mapper;

import com.msvc.healt.domain.Email;
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
        pet.setBreed(new NormalizedText(dto.breed()));
        pet.setSex(dto.sex());
        pet.setDateBorn(dto.dateBorn());
        pet.setOwner(toOwnerModel(dto.owner()));
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

    private static Owner toOwnerModel(PetRequestDto.OwnerDataDto dto) {
        if (dto == null) {
            return null;
        }
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
