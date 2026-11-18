package com.msvc.healt.domain.port.out;

import java.util.List;
import java.util.Optional;
import com.msvc.healt.domain.models.Treatment;

public interface TreatmentRepositoryPort {
    Treatment save(Treatment treatment);

    Optional<Treatment> findByIdConsultation(Long idConsultation);

    List<Treatment> findAll();
}
