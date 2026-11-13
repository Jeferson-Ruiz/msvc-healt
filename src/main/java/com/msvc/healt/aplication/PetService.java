package com.msvc.healt.aplication;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.exeption.EntityNotFoundException;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.models.Sex;
import com.msvc.healt.domain.port.in.PetUseCase;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;
import com.msvc.healt.domain.port.out.PetRepositoryPort;
import com.msvc.healt.domain.service.PetCreationValidator;

@Service
@Transactional
public class PetService implements PetUseCase {

    private final PetRepositoryPort petRepositoryPort;
    private final OwnerRepositoryPort ownerRepositoryPort;

    public PetService(PetRepositoryPort petRepositoryPort, OwnerRepositoryPort ownerRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
        this.ownerRepositoryPort = ownerRepositoryPort;
    }

    @Override
    public Pet createPet(Pet pet) {
        PetCreationValidator.ensurePetIsValid(pet);
        pet.setRegistrationDate(LocalDate.now());
        return petRepositoryPort.save(pet);
    }

    @Override
    public List<Pet> searchAll() {
        return sortedListByName(petRepositoryPort.findAll());
    }

    @Override
    public List<Pet> searchAllByspecies(String species) {
        return sortedListByName(petRepositoryPort.findAllByspecies(species));
    }

    @Override
    public List<Pet> searchAllByName(String name) {
        return sortedListByDate(petRepositoryPort.findByName(name));
    }

    @Override
    public List<Pet> searchAllBySex(String sex) {
        return sortedListByName(petRepositoryPort.findAllBySex(sex));
    }

    @Override
    public List<Pet> searchAllByAge(int age) {
        return sortedListByName(petRepositoryPort.findAllByAge(age));
    }

    @Override
    public List<Pet> searchByOwnerDocumentNumber(String number) {
        return sortedListByName(petRepositoryPort.findByOwnerDocumentNumber(number));
    }

    @Override
    public void updateName(Long id, NormalizedText name) {
        Pet pet = findPetById(id);
        pet.setName(name);
        petRepositoryPort.save(pet);
    }

    @Override
    public void updateSex(Long id, Sex sex) {
        Pet pet = findPetById(id);
        pet.setSex(sex);
        petRepositoryPort.save(pet);
    }

    @Override
    public void updateDateBorn(Long id, LocalDate date) {
        Pet pet = findPetById(id);
        pet.setDateBorn(date);
        petRepositoryPort.save(pet);
    }

    @Override
    public void updateOwner(Long idPet, String ownerDocumentNumber) {
        Pet pet = findPetById(idPet);
        Owner owner = ownerRepositoryPort.findByDocumentNumber(ownerDocumentNumber)
                .orElseThrow(() -> new EntityNotFoundException(ownerDocumentNumber));
        pet.setOwner(owner);
        petRepositoryPort.save(pet);
    }

    private Pet findPetById(Long id) {
        return petRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    private List<Pet> sortedListByDate(List<Pet> list) {
        return list.stream()
                .sorted(Comparator.comparing(Pet::getRegistrationDate))
                .toList();
    }

    private List<Pet> sortedListByName(List<Pet> list) {
        return list.stream()
                .sorted(Comparator.comparing(Pet::getName, Comparator.comparing(NormalizedText::value)))
                .toList();
    }

}
