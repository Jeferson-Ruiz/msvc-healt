package com.msvc.healt.domain.port.in;

import java.util.List;
import com.msvc.healt.domain.models.Treatment;

public interface TreatmentUseCase {
    Treatment create(Treatment treatment);

    List<Treatment> searchAll();

    Treatment searchByIdConsultation(Long idConsultation);
}
