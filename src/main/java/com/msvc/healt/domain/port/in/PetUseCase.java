package com.msvc.healt.domain.port.in;

import java.time.LocalDate;
import java.util.List;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.models.Sex;

public interface PetUseCase {

    Pet createPet(Pet pet);

    List<Pet> searchAll();

    List<Pet> searchAllByspecies(String species);

    List<Pet> searchAllByName(String name);

    List<Pet> searchAllBySex(String sex);

    List<Pet> searchAllByAge(int age);

    List<Pet> searchByOwnerDocumentNumber(String number);

    void updateName(Long id, NormalizedText name);

    void updateSex(Long id, Sex sex);

    void updateDateBorn(Long id, LocalDate born);

    void updateOwner(Long idPet, String ownerDocumentNumber);
}