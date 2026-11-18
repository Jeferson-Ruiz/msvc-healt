package com.msvc.healt.aplication;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.exeption.EntityNotFoundException;
import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.domain.models.Treatment;
import com.msvc.healt.domain.port.in.TreatmentUseCase;
import com.msvc.healt.domain.port.out.ConsultationRepositoryPort;
import com.msvc.healt.domain.port.out.TreatmentRepositoryPort;

@Service
@Transactional
public class TreatmentService implements TreatmentUseCase {

    private final TreatmentRepositoryPort treatmentRepositoryPort;
    private final ConsultationRepositoryPort consultationRepositoryPort;

    public TreatmentService(TreatmentRepositoryPort treatmentRepositoryPort,
            ConsultationRepositoryPort consultationRepositoryPort) {
        this.treatmentRepositoryPort = treatmentRepositoryPort;
        this.consultationRepositoryPort = consultationRepositoryPort;
    }

    @Override
    public Treatment create(Treatment treatment) {
        if (treatment == null) {
            throw new IllegalArgumentException("El tratamiento es obligatorio");
        }
        if (treatment.getConsultation() == null || treatment.getConsultation().getIdConsultation() == null) {
            throw new IllegalArgumentException("El id de la consulta es obligatorio");
        }
        Long consultationId = treatment.getConsultation().getIdConsultation();
        Consultation consultation = consultationRepositoryPort.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException(consultationId));
        if (treatmentRepositoryPort.findByIdConsultation(consultationId).isPresent()) {
            throw new IllegalArgumentException("La consulta ya tiene un tratamiento registrado");
        }
        treatment.setRegistrationDate(LocalDateTime.now());
        treatment.setConsultation(consultation);
        return treatmentRepositoryPort.save(treatment);
    }

    @Override
    public List<Treatment> searchAll() {
        return treatmentRepositoryPort.findAll().stream()
                .sorted(Comparator.comparing(Treatment::getRegistrationDate))
                .toList();
    }

    @Override
    public Treatment searchByIdConsultation(Long idConsultation) {
        return treatmentRepositoryPort.findByIdConsultation(idConsultation)
                .orElseThrow(() -> new EntityNotFoundException(idConsultation));
    }
}
