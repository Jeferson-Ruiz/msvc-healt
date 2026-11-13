package com.msvc.healt.domain.service;

import com.msvc.healt.domain.models.Pet;

public class PetCreationValidator {

    public static void ensurePetIsValid(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("La mascota es obligatoria");
        }
        if (pet.getOwner() == null) {
            throw new IllegalArgumentException("La mascota debe estar asociada a un propietario");
        }
    }
}
