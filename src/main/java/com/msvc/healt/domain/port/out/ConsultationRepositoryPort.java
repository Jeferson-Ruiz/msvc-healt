package com.msvc.healt.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.msvc.healt.domain.models.Consultation;

public interface ConsultationRepositoryPort {

    Consultation save(Consultation consultation);

    List<Consultation> findAll();

    Optional<Consultation> findById(Long id);

    List<Consultation> findByPetId(String id);

    List<Consultation> findByOwnerNumber(String numer);

    List<Consultation> findRangeByDate(LocalDateTime init, LocalDateTime finish);

}
