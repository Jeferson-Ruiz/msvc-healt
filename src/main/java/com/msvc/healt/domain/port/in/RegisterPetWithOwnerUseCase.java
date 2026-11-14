package com.msvc.healt.domain.port.in;

import com.msvc.healt.domain.models.Pet;

public interface RegisterPetWithOwnerUseCase {

    Pet create(Pet pet);
}
