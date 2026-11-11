package com.msvc.healt.infraestructer.adapter.owner.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;

@Repository
public class OwnerPersistenceAdapter implements OwnerRepositoryPort {

    private final OwnerJpaRepository ownerRepository;

    public OwnerPersistenceAdapter(OwnerJpaRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        OwnerEntityJpa entity = new OwnerEntityJpa();
        entity.setId(owner.getId());
        entity.setName(owner.getName().value());
        entity.setLastName(owner.getLastName().value());
        entity.setDocumentType(owner.getDocumentType().name());
        entity.setDocumentNumer(owner.getDocumentNumer().value());
        entity.setEmail(owner.getEmail().value());
        entity.setNumberPhone(owner.getNumberPhone().value());
        entity.setStatus(owner.isStatus());
        entity.setRegistrationDate(owner.getRegistrationDate());
        return toDomain(ownerRepository.save(entity));
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Owner> findByDocumentNumber(String number) {
        return ownerRepository.findByDocumentNumber(number).map(this::toDomain);
    }

    @Override
    public List<Owner> findByName(String name) {
        return ownerRepository.findByName(name)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return ownerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumer(String number) {
        return ownerRepository.existsByDocumentNumer(number);
    }

    private Owner toDomain(OwnerEntityJpa ownerJpa) {
        return new Owner(
                ownerJpa.getId(),
                new NormalizedText(ownerJpa.getName()),
                new NormalizedText(ownerJpa.getLastName()),
                DocumentType.valueOf(ownerJpa.getDocumentType()),
                new NormalizedNumber(ownerJpa.getDocumentNumer()),
                new Email(ownerJpa.getEmail()),
                new NormalizedNumber(ownerJpa.getNumberPhone()),
                ownerJpa.isStatus(),
                null,
                ownerJpa.getRegistrationDate());
    }
}
