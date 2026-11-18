package com.msvc.healt.infraestructer.adapter.treatment.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.domain.models.Treatment;
import com.msvc.healt.domain.port.out.TreatmentRepositoryPort;
import com.msvc.healt.infraestructer.adapter.consultation.out.persistence.ConsultationEntityJpa;
import com.msvc.healt.infraestructer.adapter.consultation.out.persistence.ConsultationJpaRepository;

@Repository
public class TreatmentPersistenceAdapter implements TreatmentRepositoryPort {

    private final TreatmentJpaRepository treatmentJpaRepository;
    private final ConsultationJpaRepository consultationJpaRepository;

    public TreatmentPersistenceAdapter(TreatmentJpaRepository treatmentJpaRepository,
            ConsultationJpaRepository consultationJpaRepository) {
        this.treatmentJpaRepository = treatmentJpaRepository;
        this.consultationJpaRepository = consultationJpaRepository;
    }

    @Override
    public Treatment save(Treatment treatment) {
        if (treatment == null) {
            throw new IllegalArgumentException("El tratamiento es obligatorio");
        }
        if (treatment.getConsultation() == null || treatment.getConsultation().getIdConsultation() == null) {
            throw new IllegalArgumentException(
                    "La consulta debe estar persistida antes de guardar el tratamiento");
        }
        TreatmentEntityJpa entity = new TreatmentEntityJpa();
        entity.setIdTreatment(treatment.getIdTreatment());
        entity.setRegistrationDate(treatment.getRegistrationDate());
        entity.setDateInit(treatment.getDateInit());
        entity.setDateEnd(treatment.getDateEnd());
        entity.setObservations(treatment.getObservations());
        entity.setConsultation(
                consultationJpaRepository.getReferenceById(treatment.getConsultation().getIdConsultation()));
        return toDomain(treatmentJpaRepository.save(entity));
    }

    @Override
    public Optional<Treatment> findByIdConsultation(Long idConsultation) {
        return treatmentJpaRepository.findByConsultation_IdConsultation(idConsultation)
                .map(this::toDomain);
    }

    @Override
    public List<Treatment> findAll() {
        return treatmentJpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private Treatment toDomain(TreatmentEntityJpa jpa) {
        return new Treatment(
                jpa.getIdTreatment(),
                jpa.getRegistrationDate(),
                jpa.getDateInit(),
                jpa.getDateEnd(),
                jpa.getObservations(),
                toConsultationReference(jpa.getConsultation()));
    }

    private Consultation toConsultationReference(ConsultationEntityJpa jpa) {
        if (jpa == null) {
            return null;
        }
        Consultation consultation = new Consultation();
        consultation.setIdConsultation(jpa.getIdConsultation());
        return consultation;
    }
}
