package com.msvc.healt.domain;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern FORMATO = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public Email {
        Objects.requireNonNull(value, "El email es obligatorio");
        value = value.strip().toLowerCase(Locale.ROOT);
        if (!FORMATO.matcher(value).matches()) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

}
