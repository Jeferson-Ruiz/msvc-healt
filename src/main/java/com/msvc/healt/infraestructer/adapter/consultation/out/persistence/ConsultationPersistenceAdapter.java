package com.msvc.healt.infraestructer.adapter.consultation.out.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.Consultation;
import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.models.Sex;
import com.msvc.healt.domain.port.out.ConsultationRepositoryPort;
import com.msvc.healt.infraestructer.adapter.owner.out.persistence.OwnerEntityJpa;
import com.msvc.healt.infraestructer.adapter.owner.out.persistence.OwnerJpaRepository;
import com.msvc.healt.infraestructer.adapter.pet.out.persistece.PetEntityJpa;
import com.msvc.healt.infraestructer.adapter.pet.out.persistece.PetJpaRepository;

@Repository
public class ConsultationPersistenceAdapter implements ConsultationRepositoryPort {

    private final ConsultationJpaRepository consultationJpaRepository;
    private final OwnerJpaRepository ownerJpaRepository;
    private final PetJpaRepository petJpaRepository;

    public ConsultationPersistenceAdapter(ConsultationJpaRepository consultationJpaRepository,
            OwnerJpaRepository ownerJpaRepository, PetJpaRepository petJpaRepository) {
        this.consultationJpaRepository = consultationJpaRepository;
        this.ownerJpaRepository = ownerJpaRepository;
        this.petJpaRepository = petJpaRepository;
    }

    @Override
    public Consultation save(Consultation consultation) {
        ConsultationEntityJpa entity = new ConsultationEntityJpa();
        entity.setIdConsultation(consultation.getIdConsultation());
        entity.setRegistratioDate(consultation.getRegistratioDate());
        entity.setDoctorId(consultation.getDoctorId());

        entity.setPersonCharge(consultation.getPersonCharge().value());
        entity.setDocumentNumber(consultation.getDocumentNumber().value());
        entity.setPetName(consultation.getPetName() == null ? null : consultation.getPetName().value());
        entity.setAge(consultation.getAge() == null ? null : consultation.getAge());
        entity.setSex(consultation.getSex().toString());
        entity.setReasonInquiry(consultation.getReasonInquiry());
        entity.setDiagnosis(consultation.getDiagnosis());
        entity.setObservations(consultation.getObservations());

        Owner owner = consultation.getOwner();
        if (owner != null && owner.getId() != null) {
            entity.setOwner(ownerJpaRepository.getReferenceById(owner.getId()));
        }

        Pet pet = consultation.getPet();
        if (pet != null && pet.getIdPet() != null) {
            entity.setPet(petJpaRepository.getReferenceById(pet.getIdPet()));
        }

        return toDomain(consultationJpaRepository.save(entity));
    }

    @Override
    public List<Consultation> findAll() {
        return mapToDomain(consultationJpaRepository.findAll());
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        return consultationJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Consultation> findByPetId(String id) {
        try {
            return mapToDomain(consultationJpaRepository.findByPet_IdPet(Long.valueOf(id)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El id de la mascota no es válido");
        }
    }

    @Override
    public List<Consultation> findByOwnerNumber(String numer) {
        return mapToDomain(consultationJpaRepository.findByDocumentNumber(numer));
    }

    @Override
    public List<Consultation> findRangeByDate(LocalDateTime init, LocalDateTime finish) {
        return mapToDomain(consultationJpaRepository.findByRegistratioDateBetween(init, finish));
    }

    private Consultation toDomain(ConsultationEntityJpa jpa) {
        return new Consultation(
                jpa.getIdConsultation(),
                jpa.getRegistratioDate(),
                jpa.getDoctorId(),
                jpa.getPersonCharge() == null ? null : new NormalizedText(jpa.getPersonCharge()),
                jpa.getDocumentNumber() == null ? null : new NormalizedNumber(jpa.getDocumentNumber()),
                jpa.getPetName() == null ? null : new NormalizedText(jpa.getPetName()),
                jpa.getAge(),
                jpa.getSex() == null ? null : Sex.valueOf(jpa.getSex()),
                jpa.getReasonInquiry(),
                jpa.getDiagnosis(),
                jpa.getObservations(),
                jpa.getPet() == null ? null : toPetDomain(jpa.getPet()),
                jpa.getOwner() == null ? null : toOwnerDomain(jpa.getOwner()));
    }

    private Pet toPetDomain(PetEntityJpa jpa) {
        return new Pet(
                jpa.getIdPet(),
                new NormalizedText(jpa.getName()),
                new NormalizedText(jpa.getSpecie()),
                jpa.getBreed() == null ? null : new NormalizedText(jpa.getBreed()),
                jpa.getSex() == null ? null : Sex.valueOf(jpa.getSex()),
                jpa.getDateBorn(),
                jpa.getOwner() == null ? null : toOwnerDomain(jpa.getOwner()),
                jpa.getRegistrationDate());
    }

    private Owner toOwnerDomain(OwnerEntityJpa jpa) {
        return new Owner(
                jpa.getId(),
                new NormalizedText(jpa.getName()),
                new NormalizedText(jpa.getLastName()),
                DocumentType.valueOf(jpa.getDocumentType()),
                new NormalizedNumber(jpa.getDocumentNumer()),
                new Email(jpa.getEmail()),
                new NormalizedNumber(jpa.getNumberPhone()),
                jpa.isStatus(),
                null,
                jpa.getRegistrationDate());
    }

    private List<Consultation> mapToDomain(List<ConsultationEntityJpa> listJpa) {
        return listJpa.stream()
                .map(this::toDomain)
                .toList();
    }

}
