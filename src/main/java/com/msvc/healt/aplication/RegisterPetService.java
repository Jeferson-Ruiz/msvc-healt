package com.msvc.healt.aplication;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.exeption.EntityNotFoundException;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.port.in.PetUseCase;
import com.msvc.healt.domain.port.in.RegisterPetUseCase;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;
import com.msvc.healt.domain.service.PetCreationValidator;

@Service
@Transactional
public class RegisterPetService implements RegisterPetUseCase {

    private final OwnerRepositoryPort ownerRepositoryPort;
    private final PetUseCase petUseCase;

    public RegisterPetService(OwnerRepositoryPort ownerRepositoryPort, PetUseCase petUseCase) {
        this.ownerRepositoryPort = ownerRepositoryPort;
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
                .orElseThrow(() -> new EntityNotFoundException(ownerData.getDocumentNumer().value()));

        pet.setOwner(owner);
        return petUseCase.createPet(pet);
    }
}
