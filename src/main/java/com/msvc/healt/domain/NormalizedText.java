package com.msvc.healt.domain;

import java.util.Locale;
import java.util.Objects;

public record NormalizedText(String value) {

    public NormalizedText {
        Objects.requireNonNull(value, "El texto es obligatorio");
        value = value.strip().toLowerCase(Locale.ROOT);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("El texto no puede estar vacío");
        }
    }
}
