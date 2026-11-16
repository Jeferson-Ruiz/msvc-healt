package com.msvc.healt.infraestructer.adapter.pet.out.persistece;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetJpaRepository extends JpaRepository<PetEntityJpa, Long> {
    List<PetEntityJpa> findBySpecie(String specie);

    List<PetEntityJpa> findByName(String name);

    List<PetEntityJpa> findBySex(String sex);

    List<PetEntityJpa> findByOwner_DocumentNumer(String document);

    Optional<PetEntityJpa> findByOwner_DocumentNumerAndName(String document, String name);

}
