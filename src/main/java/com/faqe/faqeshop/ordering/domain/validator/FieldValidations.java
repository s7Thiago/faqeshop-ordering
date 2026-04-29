package com.faqe.faqeshop.ordering.domain.validator;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_BLANK;

import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

public class FieldValidations {

    private FieldValidations() {
        // Construtor privado para evitar instância
    }

    public static void requiresValidEmail(String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(String email, String errorMessage) {
        Objects.requireNonNull(email, errorMessage);

        if (email.isBlank()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_EMAIL_IS_BLANK);
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
