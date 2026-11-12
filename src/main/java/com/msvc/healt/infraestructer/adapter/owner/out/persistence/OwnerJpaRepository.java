package com.msvc.healt.infraestructer.adapter.owner.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerJpaRepository extends JpaRepository<OwnerEntityJpa, Long> {
    Optional<OwnerEntityJpa> findByDocumentNumer(String number);

    List<OwnerEntityJpa> findByName(String number);

    boolean existsByEmail(String email);

    boolean existsByDocumentNumer(String number);
}
