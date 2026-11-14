package com.msvc.healt.aplication;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.port.in.OwnerUseCase;
import com.msvc.healt.domain.port.in.PetUseCase;
import com.msvc.healt.domain.port.in.RegisterPetWithOwnerUseCase;
import com.msvc.healt.domain.service.PetCreationValidator;

@Service
@Transactional
public class RegisterPetWithOwnerService implements RegisterPetWithOwnerUseCase {

    private final OwnerUseCase ownerUseCase;
    private final PetUseCase petUseCase;

    public RegisterPetWithOwnerService(OwnerUseCase ownerUseCase, PetUseCase petUseCase) {
        this.ownerUseCase = ownerUseCase;
        this.petUseCase = petUseCase;
    }

    @Override
    public Pet create(Pet pet) {
        PetCreationValidator.ensurePetIsValid(pet);
        Owner ownerData = pet.getOwner();
        if (ownerData.getDocumentNumer() == null || ownerData.getDocumentType() == null
                || ownerData.getName() == null || ownerData.getLastName() == null
                || ownerData.getEmail() == null || ownerData.getNumberPhone() == null) {
            throw new IllegalArgumentException(
                    "La información del propietario es obligatoria para registrar una nueva mascota con propietario");
        }

        Owner created = ownerUseCase.create(ownerData);
        pet.setOwner(created);
        return petUseCase.createPet(pet);
    }
}
