package com.msvc.healt.infraestructer.adapter.pet.out.persistece;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.models.DocumentType;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.models.Pet;
import com.msvc.healt.domain.models.Sex;
import com.msvc.healt.domain.port.out.PetRepositoryPort;
import com.msvc.healt.infraestructer.adapter.owner.out.persistence.OwnerEntityJpa;
import com.msvc.healt.infraestructer.adapter.owner.out.persistence.OwnerJpaRepository;

@Repository
public class PetPersistenceAdapter implements PetRepositoryPort {

    private final PetJpaRepository petJpaRepository;
    private final OwnerJpaRepository ownerJpaRepository;

    public PetPersistenceAdapter(PetJpaRepository petJpaRepository, OwnerJpaRepository ownerJpaRepository) {
        this.petJpaRepository = petJpaRepository;
        this.ownerJpaRepository = ownerJpaRepository;
    }

    @Override
    public Pet save(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("La mascota es obligatoria");
        }
        if (pet.getOwner() == null || pet.getOwner().getId() == null) {
            throw new IllegalArgumentException("El propietario debe estar persistido antes de guardar la mascota");
        }
        PetEntityJpa entity = new PetEntityJpa();
        entity.setIdPet(pet.getIdPet());
        entity.setName(pet.getName().value());
        entity.setSpecie(pet.getSpecie().value());
        entity.setBreed(pet.getBreed() == null ? null : pet.getBreed().value());
        entity.setSex(pet.getSex().toString());
        entity.setDateBorn(pet.getDateBorn());
        entity.setOwner(ownerJpaRepository.getReferenceById(pet.getOwner().getId()));
        entity.setRegistrationDate(pet.getRegistrationDate());
        return toDomain(petJpaRepository.save(entity));
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return petJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Pet> findAll() {
        return mapToDomain(petJpaRepository.findAll());
    }

    @Override
    public List<Pet> findAllByspecies(String species) {
        return mapToDomain(petJpaRepository.findBySpecie(species));
    }

    @Override
    public List<Pet> findByName(String name) {
        return mapToDomain(petJpaRepository.findByName(name));
    }

    @Override
    public List<Pet> findAllBySex(String sex) {
        return mapToDomain(petJpaRepository.findBySex(sex));
    }

    @Override
    public List<Pet> findByOwnerDocumentNumber(String number) {
        return mapToDomain(petJpaRepository.findByOwner_DocumentNumer(number));
    }

    private Pet toDomain(PetEntityJpa jpa) {
        OwnerEntityJpa ownerJpa = jpa.getOwner();
        Owner owner = ownerJpa == null ? null
                : new Owner(
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
        return new Pet(
                jpa.getIdPet(),
                new NormalizedText(jpa.getName()),
                new NormalizedText(jpa.getSpecie()),
                jpa.getBreed() == null ? null : new NormalizedText(jpa.getBreed()),
                jpa.getSex() == null ? null : Sex.valueOf(jpa.getSex()),
                jpa.getDateBorn(),
                owner,
                jpa.getRegistrationDate());
    }

    private List<Pet> mapToDomain(List<PetEntityJpa> listJpa) {
        return listJpa.stream()
                .map(this::toDomain)
                .toList();
    }

}
