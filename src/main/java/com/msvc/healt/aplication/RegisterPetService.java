package com.msvc.healt.aplication;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.port.in.OwnerUseCase;
import com.msvc.healt.domain.port.in.PetUseCase;
import com.msvc.healt.domain.port.in.RegisterPetUseCase;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;
import com.msvc.healt.domain.service.PetCreationValidator;

@Service
@Transactional
public class RegisterPetService implements RegisterPetUseCase {

    private final OwnerRepositoryPort ownerRepositoryPort;
    private final OwnerUseCase ownerUseCase;
    private final PetUseCase petUseCase;

    public RegisterPetService(OwnerRepositoryPort ownerRepositoryPort, OwnerUseCase ownerUseCase,
            PetUseCase petUseCase) {
        this.ownerRepositoryPort = ownerRepositoryPort;
        this.ownerUseCase = ownerUseCase;
        this.petUseCase = petUseCase;
    }

    @Override
    public Pet create(Pet pet) {
        PetCreationValidator.ensurePetIsValid(pet);
        Owner ownerData = pet.getOwner();
        if (ownerData.getDocumentNumer() == null) {
            throw new IllegalArgumentException("El número de documento del propietario es obligatorio");
        }

        Owner owner = ownerRepositoryPort.findByDocumentNumber(ownerData.getDocumentNumer().value())
                .orElseGet(() -> createOwner(ownerData));

        pet.setOwner(owner);
        return petUseCase.createPet(pet);
    }

    private Owner createOwner(Owner ownerData) {
        if (ownerData.getName() == null || ownerData.getLastName() == null
                || ownerData.getDocumentType() == null
                || ownerData.getEmail() == null || ownerData.getNumberPhone() == null) {
            throw new IllegalArgumentException(
                    "El propietario no existe; la información del propietario es obligatoria");
        }
        return ownerUseCase.create(ownerData);
    }
}
