package com.msvc.healt.infraestructer.adapter.consultation.out.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationJpaRepository extends JpaRepository<ConsultationEntityJpa, Long> {

    List<ConsultationEntityJpa> findByDocumentNumber(String documentNumber);

    List<ConsultationEntityJpa> findByRegistratioDateBetween(LocalDateTime init, LocalDateTime finish);

    List<ConsultationEntityJpa> findByPet_IdPet(Long idPet);
}
