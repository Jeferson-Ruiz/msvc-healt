package com.msvc.healt.aplication;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.exeption.EntityNotFoundException;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.port.in.PetUseCase;
import com.msvc.healt.domain.port.in.RegisterPetUseCase;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;
import com.msvc.healt.domain.port.out.PetRepositoryPort;
import com.msvc.healt.domain.service.PetCreationValidator;

@Service
@Transactional
public class RegisterPetService implements RegisterPetUseCase {

    private final OwnerRepositoryPort ownerRepositoryPort;
    private final PetUseCase petUseCase;
    private final PetRepositoryPort petRepositoryPort;

    public RegisterPetService(OwnerRepositoryPort ownerRepositoryPort, PetUseCase petUseCase,
            PetRepositoryPort petRepositoryPort) {
        this.ownerRepositoryPort = ownerRepositoryPort;
        this.petUseCase = petUseCase;
        this.petRepositoryPort = petRepositoryPort;
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

        petRepositoryPort.findByOwnerDocumentNumberAndName(ownerData.getDocumentNumer().value(), pet.getName().value())
                .filter(p -> p.getSpecie().equals(pet.getSpecie()))
                .ifPresent(p -> {
                    throw new IllegalArgumentException("La mascota: " + pet.getName().value() + " ya se encuentra registrada");
                });

        pet.setOwner(owner);
        return petUseCase.createPet(pet);
    }
}
