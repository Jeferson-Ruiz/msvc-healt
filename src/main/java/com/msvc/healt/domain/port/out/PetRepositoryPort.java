package com.msvc.healt.domain.port.out;

import java.util.List;
import java.util.Optional;
import com.msvc.healt.domain.models.Pet;

public interface PetRepositoryPort {

    Pet save(Pet pet);

    Optional<Pet> findById(Long id);

    List<Pet> findAll();

    List<Pet> findAllByspecies(String species);

    List<Pet> findByName(String name);

    List<Pet> findAllBySex(String sex);

    List<Pet> findByOwnerDocumentNumber(String number);

}
