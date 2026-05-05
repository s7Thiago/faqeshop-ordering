package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY;

import java.util.Objects;

public record Phone(String value) {
    public Phone {
        Objects.requireNonNull(value, VALIDATION_ERROR_PHONE_IS_NULL);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY);
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
