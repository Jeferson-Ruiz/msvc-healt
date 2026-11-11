package com.msvc.healt.domain.exeption;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Object id) {
        super("Entity not found with id: " + id);
    }
}
