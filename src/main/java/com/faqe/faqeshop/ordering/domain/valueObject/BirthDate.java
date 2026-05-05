package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST;

import java.time.LocalDate;
import java.util.Objects;

public record BirthDate(LocalDate value) {
    public BirthDate {
        Objects.requireNonNull(value, VALIDATION_ERROR_BIRTHDATE_IS_NULL);

        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);
        }
    }

    public Integer age() {
        return LocalDate.now().getYear() - value.getYear();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
