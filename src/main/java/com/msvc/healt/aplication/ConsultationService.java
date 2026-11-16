package com.msvc.healt.aplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.port.in.ConsultationUseCase;
import com.msvc.healt.domain.port.out.ConsultationRepositoryPort;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;
import com.msvc.healt.domain.port.out.PetRepositoryPort;

@Service
@Transactional
public class ConsultationService implements ConsultationUseCase {

    private final ConsultationRepositoryPort consultationRepository;
    private final OwnerRepositoryPort ownerRepositoryPort;
    private final PetRepositoryPort petRepositoryPort;

    public ConsultationService(ConsultationRepositoryPort consultationRepository,
            OwnerRepositoryPort ownerRepositoryPort, PetRepositoryPort petRepositoryPort) {
        this.consultationRepository = consultationRepository;
        this.ownerRepositoryPort = ownerRepositoryPort;
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public Consultation create(Consultation consultation) {
        if (consultation == null) {
            throw new IllegalArgumentException("La consulta es obligatoria");
        }
        if (consultation.getDocumentNumber() == null) {
            throw new IllegalArgumentException("El número de documento del propietario es obligatorio");
        }
        consultation.setRegistratioDate(LocalDateTime.now());

        Owner owner = ownerRepositoryPort.findByDocumentNumber(consultation.getDocumentNumber().value())
                .orElse(null);
        consultation.setOwner(owner);
        if (owner != null && consultation.getPetName() != null) {
            petRepositoryPort
                    .findByOwnerDocumentNumberAndName(owner.getDocumentNumer().value(), consultation.getPetName().value())
                    .ifPresent(consultation::setPet);
        }

        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> searchAll() {
        return sortByRegistrationDate(consultationRepository.findAll());
    }

    @Override
    public List<Consultation> searchByPet(String id) {
        return sortByRegistrationDate(consultationRepository.findByPetId(id));
    }

    @Override
    public List<Consultation> searchByOwner(String number) {
        return sortByRegistrationDate(consultationRepository.findByOwnerNumber(number));
    }

    @Override
    public List<Consultation> searchByDate(LocalDate init, LocalDate end) {
        LocalDateTime start = init.atStartOfDay();
        LocalDateTime finish = end == null ? LocalDateTime.now() : end.plusDays(1).atStartOfDay();
        return consultationRepository.findRangeByDate(start, finish);
    }

    private List<Consultation> sortByRegistrationDate(List<Consultation> consultations) {
        return consultations.stream()
                .sorted(Comparator.comparing(Consultation::getRegistratioDate))
                .toList();
    }

}