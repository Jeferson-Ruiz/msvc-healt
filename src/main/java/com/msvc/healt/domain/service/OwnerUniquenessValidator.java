package com.msvc.healt.domain.service;

import com.msvc.healt.domain.Email;
import com.msvc.healt.domain.NormalizedNumber;
import com.msvc.healt.domain.port.out.OwnerRepositoryPort;

public class OwnerUniquenessValidator {

    private final OwnerRepositoryPort ownerRepositoryPort;

    public OwnerUniquenessValidator(OwnerRepositoryPort ownerRepositoryPort) {
        this.ownerRepositoryPort = ownerRepositoryPort;
    }

    public void ensureDocumentNumberIsUnique(NormalizedNumber documentNumber) {
        if (ownerRepositoryPort.existsByDocumentNumer(documentNumber.value())) {
            throw new IllegalArgumentException("El número de documento ya está registrado");
        }
    }

    public void ensureEmailIsUnique(Email email) {
        if (ownerRepositoryPort.existsByEmail(email.value())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
    }
}
