package com.msvc.healt.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.msvc.healt.domain.models.Owner;

public interface OwnerRepositoryPort {

    Owner save(Owner owner);

    List<Owner> findAll();

    Optional<Owner> findById(Long id);

    Optional<Owner> findByDocumentNumber(String number);

    List<Owner> findByName(String name);

    boolean existsByEmail(String email);

    boolean existsByDocumentNumer(String number);

}
