package com.msvc.healt.infraestructer.adapter.treatment.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentJpaRepository extends JpaRepository<TreatmentEntityJpa, Long> {

    Optional<TreatmentEntityJpa> findByConsultation_IdConsultation(Long idConsultation);
}
