package com.msvc.healt.domain.port.in;

import java.time.LocalDate;
import java.util.List;
import com.msvc.healt.domain.models.Consultation;

public interface ConsultationUseCase {
    Consultation create(Consultation consultation);

    List<Consultation> searchAll();

    List<Consultation> searchByPet(String id);

    List<Consultation> searchByOwner(String number);

    List<Consultation> searchByDate(LocalDate init, LocalDate end);
}