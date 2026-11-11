package com.msvc.healt.aplication;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.NormalizedText;
import com.msvc.healt.domain.exeption.EntityNotFoundException;
import com.msvc.healt.domain.models.Owner;
import com.msvc.healt.domain.port.in.OwnerUseCase;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;

@Service
public class OwnerService implements OwnerUseCase {

    private final OwnerRepositoryPort ownerRepositoryPort;

    public OwnerService(OwnerRepositoryPort ownerRepositoryPort) {
        this.ownerRepositoryPort = ownerRepositoryPort;
    }

    @Override
    public Owner create(Owner owner) {
        validateDocument(owner.getDocumentNumer());
        validateEmail(owner.getEmail());
        owner.setRegistrationDate(LocalDate.now());
        owner.setPets(owner.getPets());
        return ownerRepositoryPort.save(owner);
    }

    @Override
    public List<Owner> searchAll() {
        return ownerRepositoryPort.findAll()
                .stream()
                .sorted(Comparator.comparing(Owner::getRegistrationDate))
                .toList();
    }

    @Override
    public Owner searchByDocumentNumber(String documentNumber) {
        return ownerRepositoryPort.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException(documentNumber));
    }

    @Override
    public List<Owner> searchByName(String name) {
        return ownerRepositoryPort.findByName(name).stream()
                .sorted(Comparator.comparing(Owner::getRegistrationDate)).toList();
    }

    @Override
    public void updateName(Long id, NormalizedText name) {
        Owner owner = findOwnerById(id);
        owner.setName(name);
        ownerRepositoryPort.save(owner);
    }

    @Override
    public void updateLastName(Long id, NormalizedText lastName) {
        Owner owner = findOwnerById(id);
        owner.setLastName(lastName);
        ownerRepositoryPort.save(owner);

    }

    @Override
    public void updateEmail(Long id, Email email) {
        Owner owner = findOwnerById(id);
        validateEmail(email);
        owner.setEmail(email);
        ownerRepositoryPort.save(owner);
    }

    @Override
    public void updatePhone(Long id, NormalizedNumber number) {
        Owner owner = findOwnerById(id);
        if (number == null) {
            throw new IllegalArgumentException();
        }
        owner.setNumberPhone(number);
        ownerRepositoryPort.save(owner);
    }

    @Override
    public void updateStatus(Long id, boolean status) {
        Owner owner = findOwnerById(id);
        owner.setStatus(status);
        ownerRepositoryPort.save(owner);
    }

    // helper
    private Owner findOwnerById(Long id) {
        return ownerRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    private void validateDocument(NormalizedNumber documentNumber) {
        if (ownerRepositoryPort.existsByDocumentNumer(documentNumber.value())) {
            throw new IllegalArgumentException();
        }
    }

    private void validateEmail(Email email) {
        if (ownerRepositoryPort.existsByEmail(email.value())) {
            throw new IllegalArgumentException();
        }

    }

}
