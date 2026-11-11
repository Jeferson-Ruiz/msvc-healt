package com.msvc.healt.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public record NormalizedNumber(String value) {

    private static final Pattern FORMATO = Pattern.compile("^[0-9]+$");

    public NormalizedNumber {
        Objects.requireNonNull(value, "El número de documento es obligatorio");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío");
        }
        if (!FORMATO.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("El número de documento solo puede contener dígitos del 0 al 9");
        }
        value = trimmed;
    }
}
