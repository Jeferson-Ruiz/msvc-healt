package com.msvc.healt.domain.port.in;

import com.msvc.healt.domain.models.Pet;

public interface RegisterPetUseCase {

    Pet create(Pet pet);
}
